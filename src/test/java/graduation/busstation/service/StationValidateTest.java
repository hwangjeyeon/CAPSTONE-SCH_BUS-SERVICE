package graduation.busstation.service;

import graduation.busstation.entity.BusStation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class StationValidateTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    StationValidate stationValidate;

    @BeforeEach
    public void before(){
        BusStation station = new BusStation();
        station.setBusStationName("후문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-81");
        station.setDateTime(LocalDateTime.now());
        station.setStationStatus("도착");
        em.persist(station);
        stationValidate = new StationValidate(em);
    }

    @Test
    public void validateTest(){
        boolean test1 = stationValidate.validateStationInfo("후문", "FC-AA-14-44-4F-81");
        boolean test2 = stationValidate.validateStationInfo("후문", "FC");
        boolean test3 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F-81");
        boolean test4 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F");

        assertThat(test1).isTrue();
        assertThat(test2).isFalse();
        assertThat(test3).isFalse();
        assertThat(test4).isFalse();

    }


}