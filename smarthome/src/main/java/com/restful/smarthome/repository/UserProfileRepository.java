package com.restful.smarthome.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Repository
public class UserProfileRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public String getDataByID(String userid) {
        String profile = "";
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient();
            DB db = mongoClient.getDB("smartHome");
            DBCollection profiles = db.getCollection("userprofiles");
            // Find the latest profile.
            DBCursor dbCursor = profiles
                    .find(new BasicDBObject("userid", userid))
                    .sort(new BasicDBObject("timestamp", -1)).limit(1);

            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                profile = dbObject.toString();
            }
        } catch (Exception e) {
            logger.info("Get profile by id failed. ", "Exception is " + e.toString());;
        }
        return profile;
    }

    public String getDataCopyByID(String userid) {
        String profile = "";
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient();
            DB db = mongoClient.getDB("smartHome");
            DBCollection profiles = db.getCollection("userprofiles_copy");
            // Find the latest profile.
            DBCursor dbCursor = profiles
                    .find(new BasicDBObject("userid", userid))
                    .sort(new BasicDBObject("timestamp", -1)).limit(1);

            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                profile = dbObject.toString();
            }
        } catch (Exception e) {
            logger.info("Get profile_copy by id failed. ", "Exception is " + e.toString());;
        }
        return profile;
    }
}
