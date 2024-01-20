package graduation.busstation.service;

import graduation.busstation.enums.StationStatus;
import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RenewStationInfoService {


    private final StationRepository stationRepository;

    public LocalDateTime renewArrivedStation(String stationName, String deviceMacAddress){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정
        BusStation busStation = stationRepository.findByBusStationName(stationName).get(0);
        busStation.setStationStatus(StationStatus.ARRIVED);
        busStation.setArrivedDateTime(LocalDateTime.now());
        return busStation.getArrivedDateTime();
    }


    public LocalDateTime renewDepartedStation(String stationName, String deviceMacAddress){
        // 이후 stationName에서 deviceMacAddress로 바꿀 예정
        BusStation busStation = stationRepository.findByBusStationName(stationName).get(0);
        busStation.setStationStatus(StationStatus.DEPARTED);
        busStation.setDepartedDateTime(LocalDateTime.now());
        return busStation.getDepartedDateTime();
    }



}
