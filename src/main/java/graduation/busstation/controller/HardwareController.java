package graduation.busstation.controller;


import graduation.busstation.dto.HardwareDto;
import graduation.busstation.service.ResetStationStatusService;
import graduation.busstation.validate.CarLicenseValidate;
import graduation.busstation.service.RenewStationInfoService;
import graduation.busstation.validate.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HardwareController {


    private final StationValidate stationValidate;
    private final CarLicenseValidate carLicenseValidate;
    private final RenewStationInfoService renewStationInfoService;
    private final ResetStationStatusService resetStationStatusService;


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
        LocalDateTime arrivedTime = renewStationInfoService.renewArrivedStation(hardwareDto.getName(),
                hardwareDto.getMacAddress());
        log.info("버스 도착 시간 = {}", arrivedTime);


    }


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


        if(hardwareDto.getName().equals("인문대앞")) {
            LocalDateTime resetTime = resetStationStatusService.resetStatus();
            log.info("최종 버스 출발 시간 = {}", resetTime);
        }else{
            // 모두 맞으면 데이터 업데이트
            LocalDateTime departedTime = renewStationInfoService.renewDepartedStation(hardwareDto.getName(),
                    hardwareDto.getMacAddress());
            log.info("버스 출발 시간 = {}", departedTime);
        }

    }


}
