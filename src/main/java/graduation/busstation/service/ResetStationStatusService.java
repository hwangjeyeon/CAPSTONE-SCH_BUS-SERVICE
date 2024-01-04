package graduation.busstation.service;

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


    private final StationRepository stationRepository;

    public LocalDateTime resetStatus(){
        List<BusStation> stationList = stationRepository.findAll();

        for (BusStation busStation : stationList) {
            busStation.setStationStatus("도착정보없음");
        }
        return LocalDateTime.now();
    }





}
