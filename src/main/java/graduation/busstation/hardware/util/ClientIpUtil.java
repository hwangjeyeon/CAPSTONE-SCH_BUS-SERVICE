package graduation.busstation.hardware.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientIpUtil {

    /**
     * 이후 개발에서 보안으로 활용할 수 있도록 하는 사용자 IP 확인 로직
     */
    public static void getRemoteIp(HttpServletRequest request){
        String ip = request.getHeader("X-FORWARDED-FOR");
        log.warn("--- 사용자 접근 IP 정보 ---");
        log.warn("--- X-FORWARDED-FOR = {}",ip);

        /**
         * 로그는 해당될때만 출력되도록 한다.
         */
        //Proxy 환경
        if(ip == null || ip.isEmpty()){
            ip = request.getHeader("Proxy-Client-IP");
            if(ip != null){
                log.warn("--- Proxy-Client-IP = {}",ip);
            }
        }
        //웹로직 환경일 경우
        if(ip == null || ip.isEmpty()){
            ip = request.getHeader("WL-Proxy-Client-IP");
            if(ip != null){
                log.warn("--- WL-Proxy-Client-IP = {}",ip);
            }

        }
        if(ip == null || ip.isEmpty()){
            ip = request.getHeader("HTTP_CLIENT_IP");
            if(ip != null){
                log.warn("--- HTTP_CLIENT_IP = {}",ip);
            }

        }
        if(ip == null || ip.isEmpty()){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            if(ip != null){
                log.warn("--- HTTP_X_FORWARDED_FOR = {}",ip);
            }
        }
        if(ip == null || ip.isEmpty()){
            ip = request.getRemoteAddr();
        }

        log.warn("--- Result : IP Address = {} ---", ip);
    }


}
