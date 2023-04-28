package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder
public class Roles {

    @JsonProperty("Name")
    private String name;


    @JsonProperty("Description")
    private String Description;


}

