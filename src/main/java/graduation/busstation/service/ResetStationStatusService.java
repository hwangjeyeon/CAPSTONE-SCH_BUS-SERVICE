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
        LocalDateTime lastDeparted = LocalDateTime.now();
        for (BusStation busStation : stationList) {
            busStation.setStationStatus("도착정보없음");
            // 마지막 정류장 출발시간 기록 안되는 문제 해결
            if(busStation.getBusStationName().equals("인문대앞")){
                busStation.setDepartedDateTime(lastDeparted);
            }
        }
        return lastDeparted;
    }





}
