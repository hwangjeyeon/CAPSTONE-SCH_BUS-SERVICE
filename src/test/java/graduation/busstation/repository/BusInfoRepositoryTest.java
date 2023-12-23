package graduation.busstation.repository;

import graduation.busstation.entity.BusInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusInfoRepositoryTest {

    @Autowired
    BusInfoRepository busInfoRepository;


    @Test
    @Transactional
    public void testBusInfo(){
        BusInfo busInfo = new BusInfo();
        busInfo.setBus_count(1);

        BusInfo savedBusInfo = busInfoRepository.save(busInfo);
        BusInfo findBusInfo = busInfoRepository.findById(savedBusInfo.getId()).get();

        Assertions.assertThat(findBusInfo).isEqualTo(savedBusInfo);
        Assertions.assertThat(findBusInfo.getBus_count()).isEqualTo(savedBusInfo.getBus_count());

    }

}