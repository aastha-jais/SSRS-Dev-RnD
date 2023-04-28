package org.example.model;//package org.open_kos.collectors.ssrs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder
public class SSRSCommonResource extends Report {

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

    //-------------------------DataSet Properties----------------------//
    @JsonProperty("HasParameters")
    private Boolean hasParameters;

    @JsonProperty("QueryExecutionTimeOut")
    private int queryExecutionTimeOut;

    //------------------------DataSource Properties---------------------//
    @JsonProperty("IsEnabled")
    private boolean isEnabled;

    @JsonProperty("ConnectionString")
    private String connectionString;

    @JsonProperty("DataSourceTypeString")
    private String dataSourceTypeString;

    @JsonProperty("IsOriginalConnectionStringExpressionBased")
    private boolean isOriginalConnectionStringExpressionBased;

    @JsonProperty("IsConnectionStringOverridden")
    private boolean isConnectionStringOverridden;

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

    //------------------------Report Properties---------------------//
    @JsonProperty("HasDataSource")
    private Boolean hasDataSource;

    @JsonProperty("HasSharedDataSets")
    private Boolean hasSharedDataSets;

    //------------------------Link Report Properties---------------------//
    @JsonProperty("Link")
    private String link;

}
