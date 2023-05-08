package org.example;

import org.example.model.Kpi;
import org.example.model.Kpis;

public class CatalogKpis {
    static void catalogKpisMetadata(Kpis Kpis) {
        for(Kpi kpi : Kpis.getValue() ){
            System.out.println("Get Name : " + kpi.getName());
            System.out.println("Get Id : " + kpi.getId());
            System.out.println("Get Type : " + kpi.getType());
            System.out.println("Get ModifiedDate : " + kpi.getModifiedDate());
            System.out.println("Get CreatedBy : " + kpi.getCreatedBy());
            System.out.println("Get Description : " + kpi.getDescription());
            System.out.println("Get ModifiedBy : " + kpi.getModifiedBy());
            System.out.println("Get Size : " + kpi.getSize());
            System.out.println("Get CreatedDate: " + kpi.getCreatedDate());
            System.out.println("Get ParentFolderId : " + kpi.getParentFolderId());
            System.out.println("Get IsFavorite : " + kpi.getIsFavorite());
            System.out.println("Get ValueFormat : " + kpi.getValueFormat());
            System.out.println("Get Currency : " + kpi.getCurrency());
            System.out.println("Get Visualization : " + kpi.getVisualization());
            System.out.println("Get Path : " + kpi.getPath());
        }
    }
}
