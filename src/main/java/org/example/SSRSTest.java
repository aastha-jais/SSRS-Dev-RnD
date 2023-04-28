package org.example;//package org.open_kos.collectors.ssrs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;
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

    public static void main(String[] args) throws Exception {
        catalogMetadata("Folders");
    }

    private static String getAPIUsingBasicAuthentication(String resourceType) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(targetURI + resourceType))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }





        public static void catalogMetadata( String resourceType) throws Exception {
            String response=getAPIUsingBasicAuthentication(resourceType);
            System.out.println(response);
              JSONObject jsnobject = new JSONObject(response);
              JSONArray jsonArray = jsnobject.getJSONArray("value");
              if (jsonArray != null) {
                  System.out.println("length: " + jsonArray.length());
                  for (int i = 0; i < jsonArray.length(); i++) {

                      ObjectMapper objectMapper = new ObjectMapper();
                      objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                      if(resourceType.equals("Folders")){
                          Folder folder = objectMapper.readValue(jsonArray.get(i).toString(), Folder.class);
                          catalogFolderMetadata(folder);
                          if(folder.getType().equals("Folder")){
                              String connectedResourceResponse=getConnectedResource(resourceType,"CatalogItems",folder.getId());
                              ResponseForConnectedSources (connectedResourceResponse, "CatalogItems");
                          }
                          String responseForPolicies=getResourceSpecificPolicies(resourceType, folder.getId());;
                          catalogPolicies(responseForPolicies);
                      }
                      else if(resourceType.equals("Reports")){
                          Report report = objectMapper.readValue(jsonArray.get(i).toString(), Report.class);
                          if(report.getType().equals("LinkedReport")){
                              catalogLinkedReportsMetadata(report);
                          }
//                          else {
//                              catalogReportMetadata(report);
//                              if (report.getHasDataSources() == true) {
//                                  String connectedResourceResponse = getConnectedResource(resourceType, "DataSources", report.getId());
//                                  ResponseForConnectedSources(connectedResourceResponse, "DataSources");
//                              }
//                              if (report.getHasSharedDataSets() == true) {
//                                  String connectedResourceResponse = getConnectedResource(resourceType, "SharedDataSets", report.getId());
//                                  ResponseForConnectedSources(connectedResourceResponse, "SharedDataSets");
//                              }
//                          }
                          String responseForPolicies=getResourceSpecificPolicies(resourceType, report.getId());;
//                        catalogPolicies(responseForPolicies);

                      }
                      else  if (resourceType.equals("DataSources")){
                          DataSources dataSources = objectMapper.readValue(jsonArray.get(i).toString(), DataSources.class);
                          catalogDatasourceMetadata(dataSources);
                          String responseForPolicies=getResourceSpecificPolicies(resourceType, dataSources.getId());;
                          catalogPolicies(responseForPolicies);
                      }
                      else if(resourceType.equals("LinkedReports")){
                          LinkedReports linkedReports = objectMapper.readValue(jsonArray.get(i).toString(), LinkedReports.class);
                          catalogLinkedReportsMetadata(linkedReports);
                          String responseForPolicies= getResourceSpecificPolicies(resourceType, linkedReports.getId());
                          catalogPolicies(responseForPolicies);
                      }
                      else if (resourceType.equals("Kpis")) {
                          Kpis kpis = objectMapper.readValue(jsonArray.get(i).toString(), Kpis.class);
                          catalogKpisMetadata(kpis);
                          String responseForPolicies = getResourceSpecificPolicies(resourceType, kpis.getId());
                          catalogPolicies(responseForPolicies);
                      }
                      else if (resourceType.equals("DataSets")) {
                          DataSets datasets = objectMapper.readValue(jsonArray.get(i).toString(), DataSets.class);
                          catalogDataSetsMetadata(datasets);
                          String responseForPolicies = getResourceSpecificPolicies(resourceType, datasets.getId());
                          catalogPolicies(responseForPolicies);
                      }
                      if(resourceType.equals("CatalogItems")) {
                          SSRSCommonResource ssrsCommonResource= objectMapper.readValue(jsonArray.get(i).toString(), SSRSCommonResource.class);
                          resourceCatalog(ssrsCommonResource);
                          String responseForPolicies=getResourceSpecificPolicies(resourceType, ssrsCommonResource.getId());
                          catalogPolicies(responseForPolicies);
                      }
                  }
              }

          }

    private static void resourceCatalog(SSRSCommonResource ssrsCommonResource) {
        if(ssrsCommonResource.getType().equals("DataSet")){

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
    private static String getConnectedResource(String resourceType,String source, String id) throws Exception {
        URI targetURI = new URI(SSRS_BASE_URI);
        String authString = USER + ":" + PASSWORD;
        String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(targetURI + resourceType + "(" + id + ")" +"/"+ source))
                .GET()
                .header("Authorization", "Basic " + authStringEnc)
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void ResponseForConnectedSources (String response, String source) throws Exception {
        System.out.println(response);
        JSONObject jsnobject = new JSONObject(response);
        JSONArray jsonArray = jsnobject.getJSONArray("value");
        if (jsonArray != null) {
            System.out.println("length: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                if(source.equals("DataSources")) {
                    System.out.println("--------------inside------------------");
                    DataSources dataSources = objectMapper.readValue(jsonArray.get(i).toString(), DataSources.class);
                    catalogDatasourceMetadata(dataSources);
                }
                else if(source.equals("SharedDataSets")) {
                    DataSets dataSets = objectMapper.readValue(jsonArray.get(i).toString(), DataSets.class);
                    catalogDataSetsMetadata(dataSets);
                }
                else if(source.equals("CatalogItems")){
                    Report report = objectMapper.readValue(jsonArray.get(i).toString(), Report.class);
                    catalogReportMetadata(report);
                    if(report.getHasDataSources()==true) {
                        String connectedResourceResponse=getConnectedResource("Reports","DataSources",report.getId());
                        ResponseForConnectedSources (connectedResourceResponse, "DataSources");
                    }
                    if(report.getHasSharedDataSets()==true) {
                        String connectedResourceResponse=getConnectedResource("Reports","SharedDataSets",report.getId());
                        ResponseForConnectedSources (connectedResourceResponse, "SharedDataSets");
                    }

                }
            }
        }
    }
    public static void catalogPolicies(String response) throws URISyntaxException, IOException, InterruptedException {

              System.out.println("Response: " + response);

              JSONObject jsnobject = new JSONObject(response);
              JSONArray jsonArray = jsnobject.getJSONArray("Policies");
        System.out.println("----Policies----");
              if (jsonArray != null) {
                  System.out.println("length: " + jsonArray.length());
                  for (int i = 0; i < jsonArray.length(); i++) {

                      ObjectMapper objectMapper = new ObjectMapper();
                      objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                      Policies obj = objectMapper.readValue(jsonArray.get(i).toString(), Policies.class);
                      System.out.println("Get Group user Name :" + obj.getGroupUserName());
                      catalogRoles(i,response);
                  }

              }
    }


    private static void catalogRoles(int index,String response) throws JsonProcessingException {

        JSONObject jsnobject = new JSONObject(response);
        JSONArray ja = jsnobject.getJSONArray("Policies").getJSONObject(index).getJSONArray("Roles");
        if (ja != null) {
            System.out.println("length: " + ja.length());
            for (int j = 0; j < ja.length(); j++) {

                ObjectMapper objectMapper1 = new ObjectMapper();
                objectMapper1.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                Roles obj1 = objectMapper1.readValue(ja.get(j).toString(), Roles.class);
                System.out.println("name : " + obj1.getName());
                System.out.println("Description : " + obj1.getDescription());

            }
        }
    }
    public static void catalogFolderMetadata(Folder folder){

        System.out.println("Get Name : " + folder.getName());
        System.out.println("Get Id : " + folder.getId());
        System.out.println("Get Type : " + folder.getType());
        System.out.println("Get ModifiedDate : " + folder.getModifiedDate());
        System.out.println("Get CreatedBy : " + folder.getCreatedBy());
        System.out.println("Get Description : " + folder.getDescription());
        System.out.println("Get ModifiedBy : " + folder.getModifiedBy());
        System.out.println("Get Size : " + folder.getSize());
        System.out.println("Get CreatedDate: " + folder.getCreatedDate());
        System.out.println("Get ParentFolderId : " + folder.getParentFolderId());
        System.out.println("Get IsFavorite : " + folder.getIsFavorite());

    }
    private static void catalogReportMetadata(Report report) {
        System.out.println("Get Name : " + report.getName());
        System.out.println("Get Id : " + report.getId());
        System.out.println("Get Type : " + report.getType());
        System.out.println("Get ModifiedDate : " + report.getModifiedDate());
        System.out.println("Get CreatedBy : " + report.getCreatedBy());
        System.out.println("Get Description : " + report.getDescription());
        System.out.println("Get ModifiedBy : " + report.getModifiedBy());
        System.out.println("Get Size : " + report.getSize());
        System.out.println("Get CreatedDate: " + report.getCreatedDate());
        System.out.println("Get ParentFolderId : " + report.getParentFolderId());
        System.out.println("Get Path : " + report.getPath());
        System.out.println("Get Hidden : " + report.getHidden());
        System.out.println("Get HasDataSources : " + report.getHasDataSources());
        System.out.println("Get HasSharedDataSets : " + report.getHasSharedDataSets());

    }
    private static void catalogDatasourceMetadata(DataSources dataSource) {
        System.out.println("Get Name : " + dataSource.getName());
        System.out.println("Get Id : " + dataSource.getId());
        System.out.println("Get Type : " + dataSource.getType());
        System.out.println("Get ModifiedDate : " + dataSource.getModifiedDate());
        System.out.println("Get CreatedBy : " + dataSource.getCreatedBy());
        System.out.println("Get Description : " + dataSource.getDescription());
        System.out.println("Get ModifiedBy : " + dataSource.getModifiedBy());
        System.out.println("Get Size : " + dataSource.getSize());
        System.out.println("Get CreatedDate: " + dataSource.getCreatedDate());
        System.out.println("Get ParentFolderId : " + dataSource.getParentFolderId());
        System.out.println("Get Path : " + dataSource.getPath());
        System.out.println("Get Hidden : " + dataSource.getHidden());
        System.out.println("Get CredentialInServer : " + dataSource.getCredentialInServer());
        System.out.println("Get CredentialRetrieval : " + dataSource.getCredentialRetrieval());
        System.out.println("Get CredentialsByUser : " + dataSource.getCredentialsByUser());
        System.out.println("Get DataSourceTypeString : " + dataSource.getDataSourceTypeString());
        System.out.println("Get IsConnectionStringOverridden : " + dataSource.getIsConnectionStringOverridden());
        System.out.println("Get DataSourceSubType : " + dataSource.getDataSourceSubType());
        System.out.println("Get IsOriginalConnectionStringExpressionBased : " + dataSource.getIsOriginalConnectionStringExpressionBased());
        System.out.println("Get ConnectionString : " + dataSource.getConnectionString());

    }
    private static void catalogLinkedReportsMetadata(LinkedReports linkedReports) {
        System.out.println("Get Name : " + linkedReports.getName());
        System.out.println("Get Id : " + linkedReports.getId());
        System.out.println("Get Type : " + linkedReports.getType());
        System.out.println("Get ModifiedDate : " + linkedReports.getModifiedDate());
        System.out.println("Get CreatedBy : " + linkedReports.getCreatedBy());
        System.out.println("Get Description : " + linkedReports.getDescription());
        System.out.println("Get ModifiedBy : " + linkedReports.getModifiedBy());
        System.out.println("Get Size : " + linkedReports.getSize());
        System.out.println("Get CreatedDate: " + linkedReports.getCreatedDate());
        System.out.println("Get ParentFolderId : " + linkedReports.getParentFolderId());
        System.out.println("Get IsFavorite : " + linkedReports.getIsFavorite());
        System.out.println("Get HasParameters : " + linkedReports.getHasParameters());
        System.out.println("Get Path : " + linkedReports.getPath());
        System.out.println("Get Link : " + linkedReports.getLink());
    }

    private static void catalogKpisMetadata(Kpis kpis) {
        System.out.println("Get Name : " + kpis.getName());
        System.out.println("Get Id : " + kpis.getId());
        System.out.println("Get Type : " + kpis.getType());
        System.out.println("Get ModifiedDate : " + kpis.getModifiedDate());
        System.out.println("Get CreatedBy : " + kpis.getCreatedBy());
        System.out.println("Get Description : " + kpis.getDescription());
        System.out.println("Get ModifiedBy : " + kpis.getModifiedBy());
        System.out.println("Get Size : " + kpis.getSize());
        System.out.println("Get CreatedDate: " + kpis.getCreatedDate());
        System.out.println("Get ParentFolderId : " + kpis.getParentFolderId());
        System.out.println("Get IsFavorite : " + kpis.getIsFavorite());
        System.out.println("Get ValueFormat : " + kpis.getValueFormat());
        System.out.println("Get Currency : " + kpis.getCurrency());
        System.out.println("Get Visualization : " + kpis.getVisualization());
        System.out.println("Get Path : " + kpis.getPath());
    }
    private static void catalogDataSetsMetadata(DataSets dataSets) {
        System.out.println("Get Name : " + dataSets.getName());
        System.out.println("Get Id : " + dataSets.getId());
        System.out.println("Get Type : " + dataSets.getType());
        System.out.println("Get ModifiedDate : " + dataSets.getModifiedDate());
        System.out.println("Get CreatedBy : " + dataSets.getCreatedBy());
        System.out.println("Get Description : " + dataSets.getDescription());
        System.out.println("Get ModifiedBy : " + dataSets.getModifiedBy());
        System.out.println("Get Size : " + dataSets.getSize());
        System.out.println("Get CreatedDate: " + dataSets.getCreatedDate());
        System.out.println("Get ParentFolderId : " + dataSets.getParentFolderId());
    }
}
