package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class BasicAuthentication {
    private static final String SSRS_BASE_URI = "http://54.152.159.202/reports/api/v2.0/";
    private static final String USER = "Administrator";
    private static final String PASSWORD = "Q2iGsk?ypc2xU($U49Y@Sg2gdJi)ZiHU";
    public static String getAPIUsingBasicAuthentication(String resourceType) throws Exception {
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
}
