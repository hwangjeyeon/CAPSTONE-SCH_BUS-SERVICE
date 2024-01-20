package graduation.busstation.service;

import graduation.busstation.enums.StationStatus;
import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ResetStationStatusServiceTest {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private ResetStationStatusService resetStationStatusService;

    @Test
    public void resetStatusTest(){
        resetStationStatusService.resetStatus();

        for (BusStation busStation : stationRepository.findAll()) {
            assertThat(busStation.getStationStatus()).isEqualTo(StationStatus.TBD);
        }
    }


}