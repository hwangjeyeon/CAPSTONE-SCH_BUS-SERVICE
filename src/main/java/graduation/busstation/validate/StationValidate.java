package graduation.busstation.validate;


import com.querydsl.jpa.impl.JPAQueryFactory;
import graduation.busstation.entity.BusStation;
import graduation.busstation.entity.QBusStation;
import graduation.busstation.repository.StationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static graduation.busstation.entity.QBusStation.busStation;

@Service
@RequiredArgsConstructor
public class StationValidate {

    private final StationRepository stationRepository;

    @Transactional
    public boolean validateStationInfo(String stationName, String stationMacAddress){


        //MacAddress으로 찾기
        List<BusStation> findDeviceMacAddress = stationRepository.findByDeviceMacAddress(stationMacAddress);
        if(findDeviceMacAddress.isEmpty() || !findDeviceMacAddress.get(0)
                .getBusStationName().equals(stationName)){
            return false;
        }

        return true;
    }

}
