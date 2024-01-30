package graduation.busstation.repository;

import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class StationRepositoryTest {


    @Autowired
    StationRepository stationRepository;

    private static final LocalDateTime NOW_DATETIME = LocalDateTime.now();

    @BeforeEach
    public void before(){
        BusStation station = new BusStation();
        station.setBusStationName("정문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-80");
        station.setArrivedDateTime(NOW_DATETIME);
        station.setDepartedDateTime(NOW_DATETIME);
        station.setStationStatus(StationStatus.DEPARTED);
        stationRepository.save(station);
    }


    @Test
    @Transactional
    public void repositorySaveTest(){
        BusStation findStation = stationRepository.findByBusStationName("정문").get(0);


        assertThat(findStation.getBusStationName()).isEqualTo("정문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-80");
        assertThat(findStation.getDepartedDateTime()).isEqualTo(NOW_DATETIME);
        assertThat(findStation.getArrivedDateTime()).isEqualTo(NOW_DATETIME);
        assertThat(findStation.getStationStatus()).isEqualTo(StationStatus.DEPARTED);
    }


}