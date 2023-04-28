package org.example.model;//package org.open_kos.collectors.ssrs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder
public class SSRSCommonResource {

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
    private Boolean hidden;

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

}