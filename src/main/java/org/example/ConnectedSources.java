package org.example;

import org.example.model.SSRSCommonResource;

import static org.example.SSRSTest.*;

public class ConnectedSources {
    public static void ResponseForConnectedSources(String response, String source) throws Exception {
//
        SSRSCommonResource[] ssrs = jsonArray(response);

        for (int i = 0; i < ssrs.length; i++) {
            if (source.equals("DataSources")) {
//                    System.out.println("--------------DataSource------------------");
//                  if(ssrs[i].getPath()!= null) {
//                      System.out.println("------SharedDataSource-------");
//                      String responseForShared=getSharedDataSourcesAndDataSets("DataSources", ssrs[i].getPath(), ssrs[i].getName());
//                      System.out.println(responseForShared);
//
//                      SSRSCommonResource[] arrayForShared=jsonArray(responseForShared);
//                      if (arrayForShared!= null) {
//                          System.out.println("length: " + arrayForShared.length);
//                          for (int j = 0; j <arrayForShared.length; j++) {
//
//                                  System.out.println("--------------DataSource------------------");
//                                  catalogDatasourceMetadata(arrayForShared[j]);
//                          }
//                      }
            } else {
                //xml parsing
                System.out.println("-----------integratedDataSources-----------");
                String responseForName = getIntegratedDataSourcesAndDataSets("Reports", ReportId);
//                              System.out.println(responseForName);
                XmlParsing.parseXmlResponse(responseForName);
//
            }


        }
//                else if(source.equals("SharedDataSets")) {
//                    System.out.println("--------------SharedDataSets------------------");
//                    DataSets dataSets = objectMapper.readValue(jsonArray.get(i).toString(), DataSets.class);
//                    if(dataSets.getPath()!= null) {
//                        System.out.println("------DataSets-------");
//                        String responseForShared=getSharedDataSourcesAndDataSets("DataSets", dataSets.getPath(), dataSets.getName());
//                        //api call using filter
//                        System.out.println(responseForShared);
//                        JSONObject jsnobjectForShared = new JSONObject(responseForShared);
//                        JSONArray jsonArrayForShared = jsnobjectForShared.getJSONArray("value");
//                        if (jsonArrayForShared != null) {
//                            System.out.println("length: " + jsonArrayForShared.length());
//                            for (int j = 0; j < jsonArray.length(); j++) {
//                                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//                                    DataSets sharedDataSets = objectMapper.readValue(jsonArray.get(j).toString(), DataSets.class);
//                                    catalogDataSetsMetadata(sharedDataSets);
//
//
//                            }
//                        }
//                    }
//
//                }
//                else if(source.equals("CatalogItems")){
//                    SSRSCommonResource ssrsCommonResource = objectMapper.readValue(jsonArray.get(i).toString(), SSRSCommonResource.class);
//                    if(ssrsCommonResource.getType().equals(("Report"))) {
//                        System.out.println("--------------Report------------------");
//                        Report report = objectMapper.readValue(jsonArray.get(i).toString(), Report.class);
//                        catalogReportMetadata(report);
//                        if (report.getHasDataSources()) {
//                            String connectedResourceResponse = getConnectedResource("Reports", "DataSources", report.getId());
//                            ResponseForConnectedSources(connectedResourceResponse, "DataSources");
//                        }
//                        if (report.getHasSharedDataSets()) {
//                            String connectedResourceResponse = getConnectedResource("Reports", "SharedDataSets", report.getId());
//                            ResponseForConnectedSources(connectedResourceResponse, "SharedDataSets");
//                        }
//                    }
//                    else if(ssrsCommonResource.getType().equals(("LinkedReport"))) {
//                        System.out.println("--------------LinkedReport------------------");
//                        LinkedReports linkedReports = objectMapper.readValue(jsonArray.get(i).toString(), LinkedReports.class);
//                        catalogLinkedReportsMetadata(linkedReports);
//                    }
//                    else if(ssrsCommonResource.getType().equals(("Folder"))) {
//                        System.out.println("--------------Folder------------------");
//                        Folder folder = objectMapper.readValue(jsonArray.get(i).toString(), Folder.class);
//                        catalogFolderMetadata(folder);
//                    }
//                }
    }

}
