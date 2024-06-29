package graduation.busstation.hardware.controller;


import graduation.busstation.hardware.dto.HardwareDto;
import graduation.busstation.hardware.entity.BusStation;
import graduation.busstation.hardware.service.RenewStationInfoService;
import graduation.busstation.hardware.template.ValidateTemplate;
import graduation.busstation.hardware.util.SecureUtil;
import graduation.busstation.hardware.validate.CarLicenseValidate;
import graduation.busstation.hardware.validate.StationValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.format.DateTimeFormatter;

import static graduation.busstation.hardware.util.SecureUtil.decryptAES256;

@Slf4j
@RestController
@RequiredArgsConstructor
@Profile("prod_hardware")
public class HardwareControllerProd {


    private final StationValidate stationValidate;
    private final CarLicenseValidate carLicenseValidate;
    private final RenewStationInfoService renewStationInfoService;
    private final ValidateTemplate validateTemplate;
    private final String FIRST_STATION = "후문";
    private final String LAST_STATION = "정문";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM월 dd일 / HH시 mm분 ss초 ");



    @PatchMapping("/hwang/sch/bus/jeyeon/station/arrived")
    public ResponseEntity<String> arrivedDataReceiveStation(@RequestBody HardwareDto hardwareDto) throws Exception {
        hardwareDto.setMacAddress(decryptAES256(hardwareDto.getMacAddress()));

        BusStation findStation = validateTemplate.validateTemplate(
                stationValidate.validateStationInfo(hardwareDto.getStationName(),
                        hardwareDto.getMacAddress()),
                carLicenseValidate.validateCarLicense(hardwareDto.getLicense()));

        log.info("버스 도착 시간 = {}", DATE_FORMAT.format(renewStationInfoService.renewArrivedStation(findStation)));
        if(hardwareDto.getStationName().equals(FIRST_STATION)) {
            renewStationInfoService.firstArrivedStation(findStation);
        }
        return new ResponseEntity<>("---버스 도착정보 등록---",HttpStatus.OK);
    }


    @PatchMapping("/hwang/sch/bus/jeyeon/station/departed")
    public ResponseEntity<String> departedDataReceiveStation(@RequestBody HardwareDto hardwareDto) throws Exception {
        hardwareDto.setMacAddress(decryptAES256(hardwareDto.getMacAddress()).toUpperCase());

        BusStation findStation = validateTemplate.validateTemplate(
                stationValidate.validateStationInfo(hardwareDto.getStationName(),
                        hardwareDto.getMacAddress()),
                carLicenseValidate.validateCarLicense(hardwareDto.getLicense()));

        log.info("버스 출발 시간 = {}", DATE_FORMAT.format(renewStationInfoService.renewDepartedStation(findStation)));
        // 최종 정류장 출발 시, 정류장 상태 초기화
        if(hardwareDto.getStationName().equals(LAST_STATION)) {
            log.info("최종 버스 출발 시간 = {}", DATE_FORMAT.format(renewStationInfoService.lastDepartedStation(findStation)));
        }

        return new ResponseEntity<>("---버스 출발정보 등록---",HttpStatus.OK);
    }


}
