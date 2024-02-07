package graduation.busstation.hardware.service;

import graduation.busstation.hardware.enums.StationStatus;
import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RenewStationInfoService {


    private final StationRepository stationRepository;

    public LocalDateTime renewArrivedStation(BusStation findStation){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정

        findStation.setStationStatus(StationStatus.ARRIVED);
        findStation.setArrivedDateTime(LocalDateTime.now());
        return findStation.getArrivedDateTime();
    }


    public LocalDateTime renewDepartedStation(BusStation findStation){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정

        findStation.setStationStatus(StationStatus.DEPARTED);
        findStation.setDepartedDateTime(LocalDateTime.now());
        return findStation.getDepartedDateTime();
    }

    public LocalDateTime lastDepartedStation(BusStation findStation){
        findStation.setDepartedDateTime(LocalDateTime.now());
        stationRepository.bulkStationStatusInit();
        return findStation.getDepartedDateTime();
    }

}
