package graduation.busstation.service;


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

import java.util.Optional;

import static graduation.busstation.entity.QBusStation.busStation;

@Service
@RequiredArgsConstructor
public class StationValidate {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public boolean validateStationInfo(String stationName, String stationMacAddress){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //stationName으로 찾기
        BusStation dbStationName = queryFactory
                .select(busStation)
                .from(busStation)
                .where(busStation.busStationName.eq(stationName))
                .fetchOne();

        //MacAddress으로 찾기
        BusStation dbStationMacAddress = queryFactory
                .select(busStation)
                .from(busStation)
                .where(busStation.deviceMacAddress.eq(stationMacAddress))
                .fetchOne();

        if(dbStationName == null || dbStationMacAddress == null){
            return false;
        }

        return true;
    }

}
