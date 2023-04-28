package org.example.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder
public class Kpis {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Path")
    private String path;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Hidden")
    private boolean hidden;

    @JsonProperty("Size")
    private int size;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;

    @JsonProperty("ModifiedDate")
    private String modifiedDate;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("CreatedDate")
    private String createdDate;

    @JsonProperty("ParentFolderId")
    private String parentFolderId;

    @JsonProperty("IsFavorite")
    private Boolean isFavorite;

    @JsonProperty("ContentType")
    private String contentType;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("ValueFormat")
    private String valueFormat;

    @JsonProperty("Visualization")
    private String visualization;

    @JsonProperty("Currency")
    private String currency;


}
