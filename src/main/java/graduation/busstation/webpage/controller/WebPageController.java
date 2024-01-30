package graduation.busstation.webpage.controller;

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


    @GetMapping("/sch/station/page")
    public String busStationPageRequest(@RequestHeader("user-agent") String userAgent, Model model){
        String userChannel =

        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());
        return "webpage.html";
    }


    @GetMapping("/sch/station/webpage")
    public String busStationWebPageRequest(Model model){
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());
        return "webpage.html";
    }


    @GetMapping("/sch/station/mobilepage")
    public String busStationMobilePageRequest(Model model){
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());
        return "webpage.html";
    }


}
