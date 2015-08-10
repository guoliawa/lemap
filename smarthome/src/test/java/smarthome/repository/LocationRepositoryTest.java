package smarthome.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;
import com.restful.smarthome.domain.Location;
import com.restful.smarthome.repository.LocationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class LocationRepositoryTest {

    @Autowired LocationRepository repository;
    
    @Autowired MongoTemplate mt;
    
    @Test
    public void readsFirstPageCorrectly() {
          Location l = new Location();
          l.setAddress("北京市海淀区滨河路靠近箭亭桥");
          l.setLatitude(40.021136);
          l.setLongitude(116.327046);
          l.setProvider("lbs");
          String date = "2015-07-23 07:26:19 0790";
          DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
          
          try {
            l.setTimestamp(format1.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          l.setUserid("A0000045ECDB8D");
          l.setUsername("guoliang");
          Location returnl = repository.insert(l);
          Location newl = new Location();
          Date now = new Date();
          

          newl.setTimestamp(now);
          newl.setAddress("山东省青岛市");
          newl.setLatitude(40.021136);
          newl.setLongitude(116.327046);
          newl.setProvider("lbs");
          newl.setUserid("A0000045ECDB8D");
          newl.setUsername("guoliang");
          repository.insert(newl);
          
          try {
            List<Location> findls = repository.findByTimestampAfter(format1.parse(date));
            List<Location> namels = repository.findByUsername("guoliang");
            repository.deleteAll();
            System.out.println("The time is " + findls.get(0).getTimestamp() + "The size is " + findls.size());
            System.out.println("The name is " + namels.get(0).getUsername() + "The size is " + namels.size());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          
          
          
//        Page<Location> locations = repository.findAll(new PageRequest(0, 10));
//        locations.getContent();
//        List<Location> ls = repository.findAll();
//        
//        String date = "2015-07-23 07:26:19 0790";
//        DateTimeFormatter myFmt = DateTimeFormatter
//                .ofPattern("yyyy-MM-dd HH:mm:ss SSSS");
//        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
//        try {
//            repository.findByTimestampAfter(format1.parse(date));
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        repository.insert(locations);
//        
//        System.out.println("Now the num is " + ls.size());
//        
//        MongoOperations mongoOps = mt;
//        ls.clear();
//        
//        ls = mongoOps.findAll(Location.class);
//        ls.clear();
//        
//        Set<String> names = mt.getCollectionNames();
//        
//        mongoOps.insertAll(locations.getContent());
//        
//        Location newl = new Location();
//        newl.setAddress("XXXX");
//        newl.setUserid("myid");
//        mongoOps.insert(newl);
//        
//        ls = mongoOps.findAll(Location.class);
//        repository.insert();
        
    }
    
    
}
