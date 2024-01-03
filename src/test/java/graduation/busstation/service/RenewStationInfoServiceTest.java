package graduation.busstation.service;

import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class RenewStationInfoServiceTest {

    @Autowired
    RenewStationInfoService renewStationInfoService;

    @Autowired
    StationRepository stationRepository;

    @BeforeEach
    public void before(){
        BusStation busStation = new BusStation();
        busStation.setBusStationName("정문");
        busStation.setDeviceMacAddress("FC-AA-14-44-4F-80");
        stationRepository.save(busStation);
    }


    @Test
    public void arrivedRenewServiceTest(){
        BusStation findStation = stationRepository.findByBusStationName("정문").get(0);
        LocalDateTime arrivedTime = renewStationInfoService.renewArrivedStation(findStation.getBusStationName(),
                findStation.getDeviceMacAddress());

        assertThat(findStation.getBusStationName()).isEqualTo("정문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-80");
        assertThat(findStation.getStationStatus()).isEqualTo("도착");
        assertThat(findStation.getArrivedDateTime()).isEqualTo(arrivedTime);
    }

    @Test
    public void departedRenewServiceTest(){
        BusStation findStation = stationRepository.findByBusStationName("정문").get(0);
        LocalDateTime departedTime = renewStationInfoService.renewDepartedStation(findStation.getBusStationName()
                , findStation.getDeviceMacAddress());

        assertThat(findStation.getBusStationName()).isEqualTo("정문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-80");
        assertThat(findStation.getStationStatus()).isEqualTo("출발");
        assertThat(findStation.getDepartedDateTime()).isEqualTo(departedTime);
    }

}