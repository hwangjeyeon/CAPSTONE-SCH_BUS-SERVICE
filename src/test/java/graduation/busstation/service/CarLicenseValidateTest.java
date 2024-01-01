package graduation.busstation.service;

import graduation.busstation.entity.CarLicense;
import graduation.busstation.repository.CarLicenseRepository;
import graduation.busstation.validate.CarLicenseValidate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CarLicenseValidateTest {

    @Autowired
    CarLicenseRepository carLicenseRepository;

    @Autowired
    CarLicenseValidate carLicenseValidate;

    @BeforeEach
    public void before(){
        CarLicense carLicense = new CarLicense();
        carLicense.setLicense("12가4519");
        carLicenseRepository.save(carLicense);
    }

    @Test
    public void validateCarLicense(){
        boolean test1 = carLicenseValidate.validateCarLicense("12가4519");
        boolean test2 = carLicenseValidate.validateCarLicense("17나9284");

        assertThat(test1).isTrue();
        assertThat(test2).isFalse();
    }


}