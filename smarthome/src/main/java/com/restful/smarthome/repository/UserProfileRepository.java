package com.restful.smarthome.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
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
            logger.info("Get profile by id failed. ",
                    "Exception is " + e.toString());
            ;
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
            logger.info("Get profile_copy by id failed. ",
                    "Exception is " + e.toString());
            ;
        }
        return profile;
    }

    public boolean updateTimepointsByID(int id, String userid, String min) {
        String profile = "";
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient();
            DB db = mongoClient.getDB("smartHome");
            DBCollection profiles = db.getCollection("userprofiles_copy");
            String timepoints = new String();
            int oldRemindnums = 0;

            if (min.equals("10")) {
                timepoints += "timepoints(600s)";
            } else {
                timepoints += "timepoints(300s)";
            }

            BasicDBObject searchQuery = new BasicDBObject("userid", userid)
                    .append("timepoints(300s).id", id);
            ;

            // Find the latest profile.
            DBCursor dbCursor = profiles.find(searchQuery)
                    .sort(new BasicDBObject("timestamp", -1)).limit(1);

            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                BasicDBList timepointslist = (BasicDBList) dbObject
                        .get("timepoints(300s)");
                BasicDBObject[] timepointsArr = timepointslist
                        .toArray(new BasicDBObject[0]);
                for (BasicDBObject timepoint : timepointsArr) {
                    // shows each item from the lights array
                    if (timepoint.get("id").equals(id)) {
                        logger.debug("###########################");
                        oldRemindnums = (int) timepoint.get("remindnums");
                        logger.debug(timepoint.get("remindnums").toString());
                        logger.debug("###########################");
                        break;
                    }
                    logger.debug("No id matches");
                }
            }

            oldRemindnums++;
            BasicDBObject updateFields = new BasicDBObject();
            updateFields.put("timepoints(300s).$.remindnums", oldRemindnums);

            DBObject updateQuery = new BasicDBObject();
            updateQuery.put("$set", updateFields);

            profiles.update(searchQuery, updateQuery, true, true);
        } catch (Exception e) {
            logger.info("Update remindnums by id failed. ",
                    "Exception is " + e.toString());
            return false;
        }
        return true;
    }
}
