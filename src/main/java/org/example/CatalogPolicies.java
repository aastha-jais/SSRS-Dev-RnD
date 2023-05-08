package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Policies;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class CatalogPolicies {

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
                CatalogRoles.catalogRoles(i,response);
            }

        }
    }
}
