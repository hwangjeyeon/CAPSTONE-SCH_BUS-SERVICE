package graduation.busstation.repository;

import graduation.busstation.entity.BusStation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StationRepositoryTest {

    @Autowired
    StationRepository stationRepository;


    @Test
    @Transactional
    public void testStation(){
        BusStation station = new BusStation();
        station.setBusStationName("후문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-81");
        station.setDateTime(LocalDateTime.now());
        station.setStationStatus("도착");

        BusStation savedStation = stationRepository.save(station);
        BusStation findStation = stationRepository.findById(savedStation.getId()).get();

        Assertions.assertThat(findStation.getBusStationName()).isEqualTo(savedStation.getBusStationName());
        Assertions.assertThat(findStation.getDeviceMacAddress()).isEqualTo(savedStation.getDeviceMacAddress());
        Assertions.assertThat(findStation.getStationStatus()).isEqualTo(savedStation.getStationStatus());
        Assertions.assertThat(findStation.getDateTime()).isEqualTo(savedStation.getDateTime());
        Assertions.assertThat(findStation).isEqualTo(savedStation);
    }


}