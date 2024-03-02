package graduation.busstation.service;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.service.RenewStationInfoService;
import graduation.busstation.hardware.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
        LocalDateTime arrivedTime = renewStationInfoService.renewArrivedStation(findStation);

        assertThat(findStation.getBusStationName()).isEqualTo("정문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-80");
        assertThat(findStation.getStationStatus()).isEqualTo(StationStatus.ARRIVED);
        assertThat(findStation.getArrivedDateTime()).isEqualTo(arrivedTime);
    }

    @Test
    public void departedRenewServiceTest(){
        BusStation findStation = stationRepository.findByBusStationName("정문").get(0);
        LocalDateTime departedTime = renewStationInfoService.renewDepartedStation(findStation);

        assertThat(findStation.getBusStationName()).isEqualTo("정문");
        assertThat(findStation.getDeviceMacAddress()).isEqualTo("FC-AA-14-44-4F-80");
        assertThat(findStation.getStationStatus()).isEqualTo(StationStatus.DEPARTED);
        assertThat(findStation.getDepartedDateTime()).isEqualTo(departedTime);
    }

}