package graduation.busstation.webpage.template;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
@Slf4j
public class BrowserReturnTemplate {

    public String getBrowserPage(String userBrowser){
        if(userBrowser.equals("mobile")){
            log.info("모바일 사용자 접근");
            return "mobilepage.html";
        }
        log.info("웹 사용자 접근");
        return "webpage.html";
    }

    public String getBrowserTimetablePage(String userBrowser){


        if(userBrowser.equals("mobile")){
            log.info("모바일 사용자 접근");
            return "mobilepage-timetable.html";
        }
        log.info("웹 사용자 접근");
        return "webpage-timetable.html";
    }




}
