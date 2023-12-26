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

@SpringBootTest
class StationRepositoryTest {


    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @Autowired
    StationRepository stationRepository;

    @BeforeEach
    public void before(){
        jpaQueryFactory = new JPAQueryFactory(em);
        BusStation station = new BusStation();
        station.setBusStationName("후문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-81");
        station.setDateTime(LocalDateTime.now());
        station.setStationStatus("도착");
        em.persist(station);
    }



    /*@Test
    @Transactional
    public void testStation(){
        BusStation savedStation = stationRepository.(station);
        BusStation findStation = stationRepository.findById(savedStation.getId()).get();

        Assertions.assertThat(findStation.getBusStationName()).isEqualTo(savedStation.getBusStationName());
        Assertions.assertThat(findStation.getDeviceMacAddress()).isEqualTo(savedStation.getDeviceMacAddress());
        Assertions.assertThat(findStation.getStationStatus()).isEqualTo(savedStation.getStationStatus());
        Assertions.assertThat(findStation.getDateTime()).isEqualTo(savedStation.getDateTime());
        Assertions.assertThat(findStation).isEqualTo(savedStation);
    }*/

    @Test
    @Transactional
    public void testQuerydslBusStationName(){
        BusStation findStation = jpaQueryFactory
                .select(busStation)
                .from(busStation)
                .where(busStation.busStationName.eq("후문"))
                .fetchOne();

        assertThat(findStation.getBusStationName()).isEqualTo("후문");
    }

    @Test
    @Transactional
    public void testQuerydslMacAddress(){
        BusStation findStation = jpaQueryFactory
                .select(busStation)
                .from(busStation)
                .where(busStation.deviceMacAddress.eq("FC-AA-14-44-4F-81"))
                .fetchOne();

        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-81");
    }





}