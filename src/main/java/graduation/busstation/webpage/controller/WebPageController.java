package graduation.busstation.webpage.controller;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import graduation.busstation.webpage.service.UserBrowserService;
import graduation.busstation.webpage.template.BrowserReturnTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@Profile({"prod_webpage","dev"})
public class WebPageController {

    private final UserBrowserService userBrowserService;
    private final BrowserReturnTemplate browserReturnTemplate;
    private final StationRepository stationRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/sch/station/page";
    }



    @GetMapping("/sch/station/page")
    public String busStationPageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        model.addAttribute("stationInfo",getPageLists());
        log.info("실시간 도착정보 페이지 요청");
        return browserReturnTemplate.getBrowserPage(
                userBrowserService.userBrowserCheck(userAgent));
    }

    @GetMapping("/sch/station/timetable")
    public String busStationTimetablePageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        model.addAttribute("stationInfo",getPageLists());
        log.info("학내순환버스 시간표 페이지 요청");
        return browserReturnTemplate.getBrowserTimetablePage(
                userBrowserService.userBrowserCheck(userAgent));
    }

    public List<BusStation> getPageLists(){
        return stationRepository.findAll();
    }
}
