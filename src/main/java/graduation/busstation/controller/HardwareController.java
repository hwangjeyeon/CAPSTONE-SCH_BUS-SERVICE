package graduation.busstation.controller;


import graduation.busstation.dto.HardwareDto;
import graduation.busstation.service.CarLicenseValidate;
import graduation.busstation.service.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HardwareController {

    @Autowired
    private final StationValidate stationValidate;

    @Autowired
    private final CarLicenseValidate carLicenseValidate;

    @GetMapping("/data/receive/station")
    public void dataReceiveStation(HardwareDto hardwareDto){
        //정류장명,mac주소가 맞는지 검증
        if(!stationValidate.validateStationInfo(hardwareDto.getName(),hardwareDto.getMacAddress())){
           log.info("접근 권한이 없습니다!");
           return;
        }

        // 차량 라이센스 정보가 맞는지 검증
        if(!carLicenseValidate.validateCarLicense(hardwareDto.getLicense())){
            log.info("등록된 버스가 아닙니다.");
            return;
        }

        // 모두 맞으면 데이터 업데이트


    }


}
