package graduation.busstation.validate;

import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.entity.BusStation;
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
        station.setBusStationName("후문");
        station.setDeviceMacAddress("FC-AA-14-44-4F-80");
        station.setArrivedDateTime(LocalDateTime.now());
        station.setDepartedDateTime(LocalDateTime.now());
        station.setStationStatus(StationStatus.ARRIVED);
        stationRepository.save(station);
    }

    @Test
    public void validateTest(){
        /*boolean test1 = stationValidate.validateStationInfo("후문", "FC-AA-14-44-4F-80");
        boolean test2 = stationValidate.validateStationInfo("후문", "FC");
        boolean test3 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F-80");
        boolean test4 = stationValidate.validateStationInfo("정문", "FC-AA-14-44-4F");

        assertThat(test1).isTrue();
        assertThat(test2).isFalse();
        assertThat(test3).isFalse();
        assertThat(test4).isFalse();*/
    }


}