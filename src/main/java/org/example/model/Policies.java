package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder
public class Policies {
    @JsonProperty("Id")
    private  String Id;

    @JsonProperty("InheritParentPolicy")
    private  String inheritParentPolicy;
    @JsonProperty("GroupUserName")
    private  String GroupUserName;
}
