package graduation.busstation.hardware.controller;


import graduation.busstation.hardware.dto.HardwareDto;
import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.template.ValidateTemplate;
import graduation.busstation.hardware.util.SecureUtil;
import graduation.busstation.hardware.validate.CarLicenseValidate;
import graduation.busstation.hardware.service.RenewStationInfoService;
import graduation.busstation.hardware.validate.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequiredArgsConstructor
@Profile("dev")
public class HardwareControllerDev {


    private final StationValidate stationValidate;
    private final CarLicenseValidate carLicenseValidate;
    private final RenewStationInfoService renewStationInfoService;
    private final ValidateTemplate validateTemplate;
    private final String STATION_NAME = "인문대앞";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM월 dd일 / HH시 mm분 ss초 ");


    @PatchMapping("/arrived/receive/station")
    public ResponseEntity<String> arrivedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
        // 보안 정보가 맞는지 검증
        if(!new SecureUtil().check(hardwareDto.getKey())){
            throw new IllegalArgumentException("검증 정보 미일치 사용자 접근");
        }

        BusStation findStation = validateTemplate.validateTemplate(
                stationValidate.validateStationInfo(hardwareDto.getStationName(),
                        hardwareDto.getMacAddress()),
                carLicenseValidate.validateCarLicense(hardwareDto.getLicense()));


        log.info("버스 도착 시간 = {}", DATE_FORMAT.format(renewStationInfoService.renewArrivedStation(findStation)));
        return new ResponseEntity<>("---버스 도착정보 등록---",HttpStatus.OK);
    }


    @PatchMapping("/departed/receive/station")
    public ResponseEntity<String> departedDataReceiveStation(@RequestBody HardwareDto hardwareDto){
        // 보안 정보가 맞는지 검증
        if(!new SecureUtil().check(hardwareDto.getKey())){
            throw new IllegalArgumentException("검증 정보 미일치 사용자 접근");
        }

        BusStation findStation = validateTemplate.validateTemplate(
                stationValidate.validateStationInfo(hardwareDto.getStationName(),
                        hardwareDto.getMacAddress()),
                carLicenseValidate.validateCarLicense(hardwareDto.getLicense()));

        // 최종 정류장 출발 시, 정류장 상태 초기화
        stationNameCheck(hardwareDto, findStation);

        return new ResponseEntity<>("---버스 출발정보 등록---",HttpStatus.OK);
    }

    private void stationNameCheck(HardwareDto hardwareDto, BusStation findStation) {
        if(hardwareDto.getStationName().equals(STATION_NAME)) {
            log.info("최종 버스 출발 시간 = {}", DATE_FORMAT.format(renewStationInfoService.lastDepartedStation(findStation)));
        }else{
            // 모두 맞으면 데이터 업데이트
            log.info("버스 출발 시간 = {}", DATE_FORMAT.format(renewStationInfoService.renewDepartedStation(findStation)));
        }
    }


}
