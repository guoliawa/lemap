package smarthome.repository;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

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
                System.out.println("User information is:" + dbObject.toString());
            }
            
            BasicDBObject docFind = new BasicDBObject("userid", "33333");
            DBObject findResult = db.getCollection("users").findOne(docFind);
            System.out.println("findResult is:" + findResult.toString());
            
            dbCursor = db.getCollection("locations").find().sort( new BasicDBObject( "timestamp" , -1 ) ).limit(1);
            for (DBObject dbObject : dbCursor) {
                dbObject.removeField("_id");
                System.out.println("User information is:" + dbObject.toString());
            }
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
