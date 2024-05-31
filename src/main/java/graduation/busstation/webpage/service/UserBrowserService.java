package graduation.busstation.webpage.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
@Slf4j
public class UserBrowserService {
    private final String[] mobilePattern = {"MOBILE"};


    public String userBrowserCheck(String userAgent){
        log.info("사용자 접근 브라우저 = {}", userAgent);
        for (String s : mobilePattern) {
            if(userAgent.toUpperCase().contains(s) && !userAgent.toUpperCase().contains("IPAD")){
                return "mobile";
            }
        }
        return "web";
    }

}
