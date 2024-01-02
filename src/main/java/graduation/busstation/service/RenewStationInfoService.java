package graduation.busstation.service;

import graduation.busstation.entity.BusStation;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RenewStationInfoService {


    private final EntityManager em;

    //TODO: UPDATE 쿼리 Querydsl로 짜기

    public void renewArrivedStation(String stationName, String deviceMacAddress, Long id){
        BusStation busStation = em.find(BusStation.class, id);
        busStation.setStationStatus("도착");
        busStation.setArrivedDateTime(LocalDateTime.now());
    }


    public void renewDepartedStation(String stationName, String deviceMacAddress, Long id){
        BusStation busStation = em.find(BusStation.class, id);
        busStation.setStationStatus("출발");
        busStation.setArrivedDateTime(LocalDateTime.now());
    }



}
