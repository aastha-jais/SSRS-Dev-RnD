package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XmlParsing {
    public static void parseXmlResponse(String responseForName)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        StringBuilder xmlStringBuilder = new StringBuilder();

        xmlStringBuilder.append(responseForName);
        ByteArrayInputStream input = new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);

        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        System.out.println("Root Element : "+root.getNodeName());


        NodeList nList = doc.getElementsByTagName("DataSet");


        System.out.println("============================");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                System.out.println("Dataset NAME : " + eElement.getAttribute("Name"));
                System.out.println(eElement.getChildNodes());
                NodeList nList1 = doc.getElementsByTagName("Query");
                System.out.println(nList1.getLength());
                if(nList1.getLength()!=0) {
                    for (int i = 0; i < nList1.getLength(); i++) {
                        System.out.println("----query----------");
                        Node node2 = nList1.item(i);
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList nList3 = doc.getElementsByTagName("DataSourceName");
                            for (int j = 0; j < nList3.getLength(); j++) {
                                System.out.println("--datasource--");
                                Node node1 = nList3.item(j);
                                if (node1.getNodeType() == Node.ELEMENT_NODE) {
                                    Element element1 = (Element) node2;
                                    System.out.println(element1.getElementsByTagName("DataSourceName").item(0).getTextContent());

                                }
                            }

                        }
                    }
                }

            }
        }

    }

}
