package org.example;//package org.open_kos.collectors.ssrs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


public class SSRSTest {

    private static final String SSRS_BASE_URI = "http://54.152.159.202/reports/api/v2.0/";
    private static final String USER = "Administrator";
    private static final String PASSWORD = "Q2iGsk?ypc2xU($U49Y@Sg2gdJi)ZiHU";

    public static String ReportId = "";

    public static void main(String[] args) throws Exception {
        catalogMetadata("Kpis");
//        parseXmlResponse(responseForName);
    }



    public static SSRSCommonResource[] jsonArray(String response) throws JsonProcessingException {
        JSONObject jsnobject = new JSONObject(response);
        JSONArray jsonArray = jsnobject.getJSONArray("value");
        SSRSCommonResource ssrsCommonResource[] = new SSRSCommonResource[jsonArray.length()];
        if (jsonArray != null) {
            System.out.println("length: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                ssrsCommonResource[i] = objectMapper.readValue(jsonArray.get(i).toString(), SSRSCommonResource.class);
            }
        }
        return ssrsCommonResource;
    }


    public static void catalogMetadata(String resourceType) throws Exception {
        String response = BasicAuthentication.getAPIUsingBasicAuthentication(resourceType);
        System.out.println(response);
        JSONObject jsnobject = new JSONObject(response);
        JSONArray jsonArray = jsnobject.getJSONArray("value");
        if (jsonArray != null) {
            System.out.println("length: " + jsonArray.length());
            for (int i = 0; i <jsonArray.length(); i++) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                if (resourceType.equals("Folders")) {
                    Folder folder = objectMapper.readValue(jsonArray.get(i).toString(), Folder.class);
                    System.out.println(("-----------Folder-------"));
                    CatalogFolder.catalogFolderMetadata(folder);
                    if (folder.getType().equals("Folder")) {
                        String connectedResourceResponse = getConnectedResource(resourceType, "CatalogItems", folder.getId());
                        ConnectedSources.ResponseForConnectedSources(connectedResourceResponse, "CatalogItems");
                    }
                    System.out.println(("-----------Policies-------"));
                    String responseForPolicies = getResourceSpecificPolicies(resourceType, folder.getId());
                    CatalogPolicies.catalogPolicies(responseForPolicies);
                } else if (resourceType.equals("Reports")) {
                    Report report = objectMapper.readValue(jsonArray.get(i).toString(), Report.class);
                    System.out.println(("-----------Reports-------"));
                    CatalogReport.catalogReportMetadata(report);
                    ReportId = report.getId();
                    if (report.getHasDataSources()) {
                        String connectedResourceResponse = getConnectedResource(resourceType, "DataSources", report.getId());
                        ConnectedSources.ResponseForConnectedSources(connectedResourceResponse, "DataSources");
                    }
                    if (report.getHasSharedDataSets()) {
                        String connectedResourceResponse = getConnectedResource(resourceType, "SharedDataSets", report.getId());
                        ConnectedSources.ResponseForConnectedSources(connectedResourceResponse, "SharedDataSets");
                    }
//                          System.out.println(("-------------------------Policies-------------------------"));
//                              String responseForPolicies=getResourceSpecificPolicies(resourceType, report.getId());;
//                        catalogPolicies(responseForPolicies);
                } else if (resourceType.equals("DataSources")) {
                    DataSources dataSources = objectMapper.readValue(jsonArray.get(i).toString(), DataSources.class);
                    CatalogDataSource.catalogDatasourceMetadata(dataSources);
                    System.out.println(("-------------------------Policies-------------------------"));
                    String responseForPolicies = getResourceSpecificPolicies(resourceType, dataSources.getId());
                    CatalogPolicies.catalogPolicies(responseForPolicies);
                } else if (resourceType.equals("LinkedReports")) {
                    LinkedReports linkedReports = objectMapper.readValue(jsonArray.get(i).toString(), LinkedReports.class);
                    CatalogLinkedReport.catalogLinkedReportsMetadata(linkedReports);
                    System.out.println(("-------------------------Policies-------------------------"));
                    String responseForPolicies = getResourceSpecificPolicies(resourceType, linkedReports.getId());
                    CatalogPolicies.catalogPolicies(responseForPolicies);
                } else if (resourceType.equals("Kpis")) {
                    Kpis Kpis = objectMapper.readValue(jsonArray.get(i).toString(), Kpis.class);
                    CatalogKpis.catalogKpisMetadata(Kpis);
                    if(jsnobject.getJSONArray("value").getJSONObject(i).optJSONObject("Data" ).optJSONObject("Goal")!=null){
                        Object ja = jsnobject.getJSONArray("value").getJSONObject(i).optJSONObject("Data" ).optJSONObject("Goal").get("Id");
                        Object obj= jsnobject.getJSONArray("value").getJSONObject(i).optJSONObject("Data" ).optJSONObject("Goal").get("Path");
                        System.out.println("Dataset Id :"+ja);
                        System.out.println("Dataset Path :"+obj);

                    }
//                    catalogData(i,response);
                    System.out.println(("-------------------------Policies-------------------------"));
//                    String responseForPolicies = getResourceSpecificPolicies(resourceType, kpiss.getId());
//                    catalogPolicies(responseForPolicies);
                } else if (resourceType.equals("DataSets")) {
                    DataSets datasets = objectMapper.readValue(jsonArray.get(i).toString(), DataSets.class);
                    CatalogDataSets.catalogDataSetsMetadata(datasets);
                    System.out.println(("-------------------------Policies-------------------------"));
                    String responseForPolicies = getResourceSpecificPolicies(resourceType, datasets.getId());
                    CatalogPolicies.catalogPolicies(responseForPolicies);
                }
//                      if(resourceType.equals("CatalogItems")) {
//                          SSRSCommonResource ssrsCommonResource= objectMapper.readValue(jsonArray.get(i).toString(), SSRSCommonResource.class);
//                          resourceCatalog(ssrsCommonResource);
//                          System.out.println(("-------------------------Policies-------------------------"));
//                          String responseForPolicies=getResourceSpecificPolicies(resourceType, ssrsCommonResource.getId());
//                          catalogPolicies(responseForPolicies);
//                      }
            }
        }

    }


    private static String getResourceSpecificPolicies(String resourceType, String id) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(targetURI + resourceType + "(" + id + ")" + "/policies"))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String getSharedDataSourcesAndDataSets(String resourceType, String path, String name) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        String uri = "";
        if (path != null) {
            uri = targetURI + resourceType + "?$filter=Path%20eq%20'" + path + "'%20&%20$Name%20eq%20'" + name + "'";
        } else {
            uri = targetURI + resourceType + "?$filter=Name%20eq%20'" + name + "'";
        }
        System.out.println(uri);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String getIntegratedDataSourcesAndDataSets(String resourceType, String id) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        String uri = targetURI + resourceType + "(" + id + ")" + "/Content/$value";
        System.out.println(uri);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String getConnectedResource(String resourceType, String source, String id) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(targetURI + resourceType + "(" + id + ")" + "/" + source))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


//}









}


