package graduation.busstation.validate;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.validate.StationValidate;
import graduation.busstation.hardware.repository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class StationValidateTest {


    @Autowired
    StationRepository stationRepository;

    @Autowired
    StationValidate stationValidate;

    @BeforeEach
    public void before(){
        BusStation station = new BusStation();
        station.setBusStationName("옆문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-80");
        station.setArrivedDateTime(LocalDateTime.now());
        station.setDepartedDateTime(LocalDateTime.now());
        station.setStationStatus(StationStatus.ARRIVED);
        stationRepository.save(station);
    }

    @Test
    public void validateTest(){
        BusStation test1 = stationValidate.validateStationInfo("옆문", "FC-AA-14-44-4F-80");
        BusStation test2 = stationValidate.validateStationInfo("옆문", "FC");
        BusStation test3 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F-80");
        BusStation test4 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F");

        assertThat(test1).isNotNull();
        assertThat(test2).isNull();
        assertThat(test3).isNull();
        assertThat(test4).isNull();
    }


}