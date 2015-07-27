package lemap.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.restful.lemap.domain.Location;
import com.restful.lemap.repository.LocationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class LocationRepositoryTest {

    @Autowired LocationRepository repository;

    @Test
    public void readsFirstPageCorrectly() {

        Page<Location> locations = repository.findAll(new PageRequest(0, 10));
        locations.getContent();
    }
    
    
}
