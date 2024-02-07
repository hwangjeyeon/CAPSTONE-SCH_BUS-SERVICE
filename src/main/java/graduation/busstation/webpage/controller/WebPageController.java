package graduation.busstation.webpage.controller;

import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.repository.StationRepository;
import graduation.busstation.webpage.service.UserBrowserService;
import graduation.busstation.webpage.template.BrowserReturnTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
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
        return browserReturnTemplate.getBrowserPage(
                userBrowserService.userBrowserCheck(userAgent));
    }

    @GetMapping("/sch/station/timetable")
    public String busStationTimetablePageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        model.addAttribute("stationInfo",getPageLists());
        return browserReturnTemplate.getBrowserTimetablePage(
                userBrowserService.userBrowserCheck(userAgent));
    }

    public List<BusStation> getPageLists(){
        return stationRepository.findAll();
    }
}
