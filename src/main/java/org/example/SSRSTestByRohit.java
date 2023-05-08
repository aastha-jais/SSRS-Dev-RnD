package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SSRSTestByRohit {

    private static final String SSRS_BASE_URI = "http://54.152.159.202/reports/api/v2.0/";
    private static final String USER = "Administrator";
    private static final String PASSWORD = "Q2iGsk?ypc2xU($U49Y@Sg2gdJi)ZiHU";

    public static void main(String[] args) throws Exception {
        parseXmlResponse();
    }

    private static void parseXmlResponse() throws Exception {
        String response = getSharedDataSourcesAndDataSets();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(response);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("DataSet");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                NodeList queryList = ((Element) nList.item(temp)).getElementsByTagName("Query");
                NodeList shareDataSetList = ((Element) nList.item(temp)).getElementsByTagName("SharedDataSet");

                getDatasetDataSourceDetails(queryList, element, "Query");
                getDatasetDataSourceDetails(shareDataSetList, element, "SharedDataSet");

            }
        }
    }

    private static String getSharedDataSourcesAndDataSets() throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        String uri = "http://54.152.159.202/reports/api/v2.0/Reports(80b7dc36-6a13-470f-8537-70226f6aee59)" +
                "/Content/$value";
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).GET()
                                             .header("Authorization", "Basic " + authStringEnc).build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        return response.body();
    }

    private static void getDatasetDataSourceDetails(NodeList nodeList, Element element, String nodeType) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("Dataset Name :: " + element.getAttribute("Name"));
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(nodeType)) {
                NodeList childNodeList = nodeList.item(i).getChildNodes();

                if (childNodeList.getLength() != 0) {
                    for (int j = 0; j < childNodeList.getLength(); j++) {
                        Node childNode = childNodeList.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNode;

                            if (childElement.getTagName().equals("DataSourceName")) {
                                System.out.println(
                                        childElement.getTagName() + "::" + childElement.getTextContent().trim());
                            }
                            if (childElement.getTagName().equals("CommandText")) {
                                System.out.println(
                                        childElement.getTagName() + "::" + childElement.getTextContent().trim());
                            }
                            if (childElement.getTagName().equals("SharedDataSetReference")) {
                                System.out.println(
                                        childElement.getTagName() + "::" + childElement.getTextContent().trim());
                            }
                        }
                    }
                }
                System.out.println("----------------------------------------------------------------------------");
            }
        }
    }
}
