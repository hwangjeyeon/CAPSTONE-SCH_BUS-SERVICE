package graduation.busstation.validate;


import com.querydsl.jpa.impl.JPAQueryFactory;
import graduation.busstation.entity.CarLicense;
import graduation.busstation.entity.QCarLicense;
import graduation.busstation.repository.CarLicenseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static graduation.busstation.entity.QCarLicense.carLicense;

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
