package graduation.busstation.service;

import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        BusStation busStation = stationRepository.findByBusStationName(stationName).get(0);
        busStation.setStationStatus("도착");
        busStation.setArrivedDateTime(LocalDateTime.now());
        return busStation.getArrivedDateTime();
    }


    public LocalDateTime renewDepartedStation(String stationName, String deviceMacAddress){
        BusStation busStation = stationRepository.findByBusStationName(stationName).get(0);
        busStation.setStationStatus("출발");
        busStation.setDepartedDateTime(LocalDateTime.now());
        return busStation.getDepartedDateTime();
    }



}
