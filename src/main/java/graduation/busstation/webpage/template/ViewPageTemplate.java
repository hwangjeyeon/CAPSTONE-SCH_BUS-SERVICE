package graduation.busstation.webpage.template;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ViewPageTemplate {

    private final StationRepository stationRepository;

    public List<BusStation> getPageLists(){
        return stationRepository.findAll();
    }



}
