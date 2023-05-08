package org.example;

import org.example.model.Folder;

public class CatalogFolder {
    public static void catalogFolderMetadata(Folder folder){

        System.out.println("Get Name : " + folder.getName());
        System.out.println("Get Id : " + folder.getId());
        System.out.println("Get Type : " + folder.getType());
        System.out.println("Get ModifiedDate : " + folder.getModifiedDate());
        System.out.println("Get CreatedBy : " + folder.getCreatedBy());
        System.out.println("Get Description : " + folder.getDescription());
        System.out.println("Get ModifiedBy : " + folder.getModifiedBy());
        System.out.println("Get Size : " + folder.getSize());
        System.out.println("Get CreatedDate: " + folder.getCreatedDate());
        System.out.println("Get ParentFolderId : " + folder.getParentFolderId());
        System.out.println("Get IsFavorite : " + folder.getIsFavorite());

    }
}
