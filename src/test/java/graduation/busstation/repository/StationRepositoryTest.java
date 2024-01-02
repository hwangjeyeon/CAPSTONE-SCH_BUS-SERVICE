package graduation.busstation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduation.busstation.entity.BusStation;
import graduation.busstation.entity.QBusStation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static graduation.busstation.entity.QBusStation.busStation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class StationRepositoryTest {


    @Autowired
    StationRepository stationRepository;

    private static final LocalDateTime NOW_DATETIME = LocalDateTime.now();

    @BeforeEach
    public void before(){
        BusStation station = new BusStation();
        station.setBusStationName("후문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-81");
        station.setArrivedDateTime(NOW_DATETIME);
        station.setDepartedDateTime(NOW_DATETIME);
        station.setStationStatus("출발");
        stationRepository.save(station);
    }


    @Test
    @Transactional
    public void repositorySaveTest(){
        BusStation findStation = stationRepository.findByBusStationName("후문").get(0);


        assertThat(findStation.getBusStationName()).isEqualTo("후문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-81");
        assertThat(findStation.getDepartedDateTime()).isEqualTo(NOW_DATETIME);
        assertThat(findStation.getArrivedDateTime()).isEqualTo(NOW_DATETIME);
        assertThat(findStation.getStationStatus()).isEqualTo("출발");
    }


}