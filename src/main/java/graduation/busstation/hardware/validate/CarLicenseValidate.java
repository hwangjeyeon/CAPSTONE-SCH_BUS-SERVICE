package graduation.busstation.hardware.validate;


import graduation.busstation.hardware.entity.CarLicense;
import graduation.busstation.hardware.repository.CarLicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarLicenseValidate {

    @Autowired
    private final CarLicenseRepository carLicenseRepository;

    @Transactional
    public boolean validateCarLicense(String license){
        Optional<CarLicense> findLicense = carLicenseRepository.findById(license);
        if(findLicense.isEmpty()){
            return false;
        }
        return true;
    }



}
