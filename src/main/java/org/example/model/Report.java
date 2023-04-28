package org.example.model;//package org.open_kos.collectors.ssrs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonPropertyOrder
public class Report extends LinkedReports{

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

    @JsonProperty("IsFavorite")
    private Boolean isFavorite;

    @JsonProperty("ContentType")
    private String contentType;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("Roles")
    private List<String> roles;

    @JsonProperty("HasDataSources")
    private Boolean hasDataSources;

    @JsonProperty("HasSharedDataSets")
    private Boolean hasSharedDataSets;

}
