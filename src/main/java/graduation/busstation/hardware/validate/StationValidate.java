package graduation.busstation.hardware.validate;


import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationValidate {

    private final StationRepository stationRepository;

    @Transactional
    public BusStation validateStationInfo(String stationName, String stationMacAddress){


        //MacAddress으로 찾기
        List<BusStation> findDeviceMacAddress = stationRepository.findByDeviceMacAddress(stationMacAddress);
        if(findDeviceMacAddress.isEmpty() || !findDeviceMacAddress.get(0)
                .getBusStationName().equals(stationName)){
            return null;
        }

        return findDeviceMacAddress.get(0);
    }

}
