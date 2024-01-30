package graduation.busstation.hardware.service;

import graduation.busstation.hardware.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
