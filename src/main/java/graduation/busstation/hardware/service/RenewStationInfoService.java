package graduation.busstation.hardware.service;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RenewStationInfoService {


    private final StationRepository stationRepository;

    @Transactional
    public LocalDateTime renewArrivedStation(BusStation findStation){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정

        findStation.setArrivedAndStatus(LocalDateTime.now(), StationStatus.ARRIVED);
        return findStation.getArrivedDateTime();
    }

    @Transactional
    public LocalDateTime renewDepartedStation(BusStation findStation){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정

        findStation.setDepartedAndStatus(LocalDateTime.now(), StationStatus.DEPARTED);
        return findStation.getDepartedDateTime();
    }

    @Transactional
    public LocalDateTime lastDepartedStation(BusStation findStation){
        stationRepository.bulkStationStatusInit();

        return findStation.getDepartedDateTime();
    }

    @Transactional
    public LocalDateTime firstArrivedStation(BusStation findStation){
        stationRepository.lastStationStatusInit();

        return findStation.getArrivedDateTime();
    }

}
