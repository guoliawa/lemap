package smarthome.repository;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.restful.smarthome.domain.LocationInfo;
import com.restful.smarthome.repository.LocationInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class FindByDateBetween {
    @Autowired LocationInfoRepository repository;
    
    @Test
    public void test() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String startDateInString = "10/08/2015";
        String endDateInString = "11/08/2015";
        String userid = "460030991824172";
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date start = formatter.parse(startDateInString);
            Date end = formatter.parse(endDateInString);
            System.out.println(start);
            System.out.println(formatter.format(end));
            List<LocationInfo> locations = repository.findByUseridAndTimestampBetweenOrderByTimestampDesc(userid, start, end);
            
            for (LocationInfo lo : locations) {
                System.out.println(lo.getTimestamp());
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
