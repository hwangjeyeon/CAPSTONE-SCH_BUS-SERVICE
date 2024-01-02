package graduation.busstation.controller;


import graduation.busstation.dto.HardwareDto;
import graduation.busstation.validate.CarLicenseValidate;
import graduation.busstation.service.RenewStationInfoService;
import graduation.busstation.validate.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HardwareController {

    @Autowired
    private final StationValidate stationValidate;

    @Autowired
    private final CarLicenseValidate carLicenseValidate;

    @Autowired
    private final RenewStationInfoService renewStationInfoService;

    @PostMapping("/arrived/receive/station")
    public void arrivedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
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
        renewStationInfoService.renewArrivedStation(hardwareDto.getName(), hardwareDto.getMacAddress(), 1L);
    }

    //TODO: 버스가 출발한 경우를 업데이트하기 위한 컨트롤러
    @PostMapping("/departed/receive/station")
    public void departedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
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
        renewStationInfoService.renewDepartedStation(hardwareDto.getName(), hardwareDto.getMacAddress(), 1L);
    }


}
