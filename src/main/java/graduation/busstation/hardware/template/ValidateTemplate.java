package graduation.busstation.hardware.template;


import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.util.ClientIpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateTemplate {


    public BusStation validateTemplate(BusStation findStation, Boolean carLicenseValidate, HttpServletRequest request){

        //정류장명,mac주소가 맞는지 검증
        if(findStation == null){
            ClientIpUtil.getRemoteIp(request);
            throw new IllegalArgumentException("잘못된 정류장/MAC 주소 접근");
        }

        // 차량 라이센스 정보가 맞는지 검증
        if(!carLicenseValidate){
            ClientIpUtil.getRemoteIp(request);
            throw new IllegalArgumentException("미등록 번호판 접근");
        }

        return findStation;
    }

}
