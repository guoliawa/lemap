package smarthome.repository;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class TestGetUserprofile {

    @Test
    public void test() {
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient();
            DB db = mongoClient.getDB("smartHome");
            long number = db.getCollection("locations").count();
            DBCursor dbCursor = db.getCollection("users").find();

            System.out.println("The num of location is " + number);

            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                System.out
                        .println("User information is:" + dbObject.toString());
            }

            BasicDBObject docFind = new BasicDBObject("userid", "33333");
            DBObject findResult = db.getCollection("users").findOne(docFind);
            System.out.println("findResult is:" + findResult.toString());

            dbCursor = db.getCollection("locations").find()
                    .sort(new BasicDBObject("timestamp", -1)).limit(1);
            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                System.out
                        .println("User information is:" + dbObject.toString());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindArray() {
        String profile = "";
        String id = "1";
        String userid = "A10000453292BF";
        String min = "5";
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient();
            DB db = mongoClient.getDB("smartHome");
            DBCollection profiles = db.getCollection("userprofiles_copy");
            String timepoints = "timepoints";

            if (min.equals("5")) {
                timepoints += "(300s)";
            } else if (min.equals("10")) {
                timepoints += "(600s)";
            } else {
                timepoints += "(300s)";
            }

            BasicDBObject searchQuery = new BasicDBObject("userid", userid)
                    .append("timepoints(300s).id", 1);

            Query query = new Query();

            query.addCriteria(Criteria.where("userid").is(userid)
                    .andOperator(Criteria.where("timepoints(300s).id").is(1)));

            BasicDBObject updateFields = new BasicDBObject();
            updateFields.put("timepoints(300s).$.remindnums", 9);

            DBObject updateQuery = new BasicDBObject();
            updateQuery.put("$set", updateFields);

            profiles.update(searchQuery, updateQuery, true, true);

            DBCursor dbCursor = profiles.find(searchQuery)
                    .sort(new BasicDBObject("timestamp", -1)).limit(1);
            ;

            for (DBObject dbObject : dbCursor) {
                BasicDBList timepointlist = (BasicDBList) dbObject
                        .get("timepoints(300s)");
                BasicDBObject[] lightArr = timepointlist
                        .toArray(new BasicDBObject[0]);
                for (BasicDBObject dbObj : lightArr) {
                    // shows each item from the lights array
                    if (dbObj.get("id").equals(1)) {
                        System.out.println("###########################");
                        System.out.println(dbObj.get("remindnums"));
                        System.out.println("###########################");
                    }
                    System.out.println(dbObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
