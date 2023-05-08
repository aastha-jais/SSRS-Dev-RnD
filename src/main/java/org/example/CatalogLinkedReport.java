package org.example;

import org.example.model.LinkedReports;

public class CatalogLinkedReport {
    public static void catalogLinkedReportsMetadata(LinkedReports linkedReports) {
        System.out.println("Get Name : " + linkedReports.getName());
        System.out.println("Get Id : " + linkedReports.getId());
        System.out.println("Get Type : " + linkedReports.getType());
        System.out.println("Get ModifiedDate : " + linkedReports.getModifiedDate());
        System.out.println("Get CreatedBy : " + linkedReports.getCreatedBy());
        System.out.println("Get Description : " + linkedReports.getDescription());
        System.out.println("Get ModifiedBy : " + linkedReports.getModifiedBy());
        System.out.println("Get Size : " + linkedReports.getSize());
        System.out.println("Get CreatedDate: " + linkedReports.getCreatedDate());
        System.out.println("Get ParentFolderId : " + linkedReports.getParentFolderId());
        System.out.println("Get IsFavorite : " + linkedReports.getIsFavorite());
        System.out.println("Get HasParameters : " + linkedReports.getHasParameters());
        System.out.println("Get Path : " + linkedReports.getPath());
        System.out.println("Get Link : " + linkedReports.getLink());
    }
}
