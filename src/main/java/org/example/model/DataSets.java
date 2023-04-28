package org.example.model;//package org.open_kos.collectors.ssrs.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder
public class DataSets extends SSRSCommonResource{

    @JsonProperty("HasParameters")
    private boolean hasParameters;

    @JsonProperty("QueryExecutionTimeOut")
    private int queryExecutionTimeOut;

    private SSRSCommonResource SSRSCommonResource;
}