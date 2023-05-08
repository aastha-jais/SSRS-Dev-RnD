package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Roles;
import org.json.JSONArray;
import org.json.JSONObject;

public class CatalogRoles {
    static void catalogRoles(int index, String response) throws JsonProcessingException {

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
}
