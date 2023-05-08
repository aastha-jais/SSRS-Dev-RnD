package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@lombok.Data
@JsonPropertyOrder
public class Data {
    @JsonProperty("Value")
    private  String value;

    @JsonProperty("Goal")
    private  String goal;


}
