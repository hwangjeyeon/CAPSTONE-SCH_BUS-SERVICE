package graduation.busstation.template;

import graduation.busstation.entity.BusStation;
import graduation.busstation.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
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
