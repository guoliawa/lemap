package com.restful.smarthome.repository;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

public class FileRepository {
    
    @Autowired
    GridFsOperations operations;
    
     public String storeFile(InputStream in) {
         return "successfully";
     }
}
