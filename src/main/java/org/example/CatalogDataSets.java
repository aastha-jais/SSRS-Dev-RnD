package org.example;

import org.example.model.DataSets;

public class CatalogDataSets {

    public static void catalogDataSetsMetadata(DataSets dataSets) {
        System.out.println("Get Name : " + dataSets.getName());
//        System.out.println("Get Id : " + dataSets.getId());
//        System.out.println("Get Type : " + dataSets.getType());
        System.out.println("Get Path : " + dataSets.getPath());
//        System.out.println("Get ModifiedDate : " + dataSets.getModifiedDate());
//        System.out.println("Get CreatedBy : " + dataSets.getCreatedBy());
//        System.out.println("Get Description : " + dataSets.getDescription());
//        System.out.println("Get ModifiedBy : " + dataSets.getModifiedBy());
//        System.out.println("Get Size : " + dataSets.getSize());
//        System.out.println("Get CreatedDate: " + dataSets.getCreatedDate());
        System.out.println("Get ParentFolderId : " + dataSets.getParentFolderId());
    }
}
