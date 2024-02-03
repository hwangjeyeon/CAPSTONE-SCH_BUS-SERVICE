package graduation.busstation.webpage.controller;

import graduation.busstation.webpage.service.UserBrowserService;
import graduation.busstation.webpage.template.ViewPageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebPageController {



    private final ViewPageTemplate viewPageTemplate;
    private final UserBrowserService userBrowserService;

    @GetMapping("/")
    public String home(){
        return "redirect:/sch/station/page";
    }



    @GetMapping("/sch/station/page")
    public String busStationPageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        String userChannel = userBrowserService.userBrowserCheck(userAgent);
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());

        if(userChannel.equals("mobile")){
            log.info("모바일 사용자 접근");
            return "webpage.html";
        }
        log.info("웹 사용자 접근");
        return "webpage.html";
    }

    @GetMapping("/sch/station/timetable")
    public String busStationTimetablePageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        String userChannel = userBrowserService.userBrowserCheck(userAgent);
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());

        if(userChannel.equals("mobile")){
            log.info("모바일 사용자 접근");
            return "webpage-timetable.html";
        }
        log.info("웹 사용자 접근");
        return "webpage-timetable.html";
    }


}
