package graduation.busstation.controller;


import graduation.busstation.dto.HardwareDto;
import graduation.busstation.service.ResetStationStatusService;
import graduation.busstation.validate.CarLicenseValidate;
import graduation.busstation.service.RenewStationInfoService;
import graduation.busstation.validate.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @PatchMapping("/arrived/receive/station")
    public ResponseEntity<String> arrivedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
        //정류장명,mac주소가 맞는지 검증
        if(!stationValidate.validateStationInfo(hardwareDto.getStationName(),hardwareDto.getMacAddress())){
            throw new IllegalArgumentException("잘못된 정류장/MAC 주소 접근");
        }

        // 차량 라이센스 정보가 맞는지 검증
        if(!carLicenseValidate.validateCarLicense(hardwareDto.getLicense())){
            throw new IllegalArgumentException("미등록 번호판 접근");
        }

        // 모두 맞으면 데이터 업데이트
        LocalDateTime arrivedTime = renewStationInfoService.renewArrivedStation(hardwareDto.getStationName(),
                hardwareDto.getMacAddress());
        log.info("버스 도착 시간 = {}", arrivedTime);

        return new ResponseEntity<>("---버스 도착정보 등록---",HttpStatus.OK);
    }


    @PatchMapping("/departed/receive/station")
    public ResponseEntity<String> departedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
        //정류장명,mac주소가 맞는지 검증
        if(!stationValidate.validateStationInfo(hardwareDto.getStationName(),hardwareDto.getMacAddress())){
            throw new IllegalArgumentException("잘못된 정류장/MAC 주소 접근");
        }

        // 차량 라이센스 정보가 맞는지 검증
        if(!carLicenseValidate.validateCarLicense(hardwareDto.getLicense())){
            throw new IllegalArgumentException("미등록 번호판 접근");
        }

        // 최종 정류장 출발 시, 정류장 상태 초기화
        if(hardwareDto.getStationName().equals("인문대앞")) {
            log.info("최종 버스 출발 시간 = {}", resetStationStatusService.resetStatus());
        }else{
            // 모두 맞으면 데이터 업데이트
            log.info("버스 출발 시간 = {}", renewStationInfoService.renewDepartedStation(hardwareDto.getStationName()
                    , hardwareDto.getMacAddress()));
        }

        return new ResponseEntity<>("---버스 출발정보 등록---",HttpStatus.OK);
    }


}
