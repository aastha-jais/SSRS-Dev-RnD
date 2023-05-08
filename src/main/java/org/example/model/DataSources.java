package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder
public class DataSources{

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

    @JsonProperty("IsEnabled")
    private boolean isEnabled;

    @JsonProperty("ConnectionString")
    private String connectionString;

    @JsonProperty("DataSourceTypeString")
    private String dataSourceTypeString;

    @JsonProperty("IsOriginalConnectionStringExpressionBased")
    private Boolean isOriginalConnectionStringExpressionBased;

    @JsonProperty("IsConnectionStringOverridden")
    private Boolean isConnectionStringOverridden;

    @JsonProperty("DataSourceType")
    private String dataSourceType;

    @JsonProperty("CredentialRetrieval")
    private String credentialRetrieval;

    @JsonProperty("IsReference")
    private String isReference;

    @JsonProperty("DataSourceSubType")
    private String dataSourceSubType;

    @JsonProperty("DataModelDataSource")
    private String dataModelDataSource;

    @JsonProperty("CredentialsByUser")
    private String credentialsByUser;

    @JsonProperty("CredentialInServer")
    private String credentialInServer;

}
