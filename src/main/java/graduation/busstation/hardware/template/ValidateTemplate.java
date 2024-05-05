package graduation.busstation.hardware.template;


import graduation.busstation.hardware.entity.BusStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateTemplate {


    public BusStation validateTemplate(BusStation findStation, Boolean carLicenseValidate){

        //정류장명,mac주소가 맞는지 검증
        if(findStation == null){

            throw new IllegalArgumentException("잘못된 정류장/MAC 주소 접근");
        }

        // 차량 라이센스 정보가 맞는지 검증
        if(!carLicenseValidate){
            throw new IllegalArgumentException("미등록 번호판 접근");
        }

        return findStation;
    }

}
