package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder
public class Goal {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Path")
    private String path;



}
