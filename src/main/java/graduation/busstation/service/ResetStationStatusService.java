package graduation.busstation.service;

import graduation.busstation.enums.StationStatus;
import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResetStationStatusService {

    private final String STATION_NAME = "인문대앞";
    private final StationRepository stationRepository;

    public void resetStatus(){
        stationRepository.bulkStationStatusInit();
    }

}
