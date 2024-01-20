package graduation.busstation.controller;

import graduation.busstation.entity.BusStation;
import graduation.busstation.template.ViewPageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebPageController {



    private final ViewPageTemplate viewPageTemplate;

    @GetMapping("/sch/station/webpage")
    public String busStationWebPageRequest(Model model){
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());
        return "index.html";
    }


    @GetMapping("/sch/station/mobilepage")
    public String busStationMobilePageRequest(Model model){
        model.addAttribute("stationInfo",viewPageTemplate.getPageLists());
        return "index.html";
    }


}
