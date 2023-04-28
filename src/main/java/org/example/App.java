package org.example;

import net.snowflake.client.jdbc.internal.apache.http.auth.AuthScope;
import net.snowflake.client.jdbc.internal.apache.http.auth.Credentials;
import net.snowflake.client.jdbc.internal.apache.http.auth.UsernamePasswordCredentials;
import net.snowflake.client.jdbc.internal.apache.http.client.CredentialsProvider;
import net.snowflake.client.jdbc.internal.apache.http.client.methods.CloseableHttpResponse;
import net.snowflake.client.jdbc.internal.apache.http.client.methods.HttpGet;
import net.snowflake.client.jdbc.internal.apache.http.impl.client.BasicCredentialsProvider;
import net.snowflake.client.jdbc.internal.apache.http.impl.client.CloseableHttpClient;
import net.snowflake.client.jdbc.internal.apache.http.impl.client.HttpClientBuilder;
import net.snowflake.client.jdbc.internal.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        //Create an object of credentialsProvider
        CredentialsProvider credentialsPovider = new BasicCredentialsProvider();

        //Set the credentials
        AuthScope scope = new AuthScope("http://54.152.159.202/reports/api/v2.0/", 80);

        Credentials credentials = new UsernamePasswordCredentials("Administrator", "Q2iGsk?ypc2xU($U49Y@Sg2gdJi)ZiHU");
        credentialsPovider.setCredentials(scope,credentials);

        //Creating the HttpClientBuilder
        HttpClientBuilder clientbuilder = HttpClients.custom();

        //Setting the credentials
        clientbuilder = clientbuilder.setDefaultCredentialsProvider(credentialsPovider);

        //Building the CloseableHttpClient object
        CloseableHttpClient httpclient = clientbuilder.build();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet("http://54.152.159.202/reports/api/v2.0/Folders");

        //Printing the method used
        System.out.println(httpget.getMethod());

//        Executing the Get request

        CloseableHttpResponse httpresponse = httpclient.execute(httpget);

        //Printing the status line
        System.out.println(httpresponse.getAllHeaders());
//        int statusCode = httpresponse.statusCode().getStatusCode();
//        System.out.println(statusCode);
    }
}
