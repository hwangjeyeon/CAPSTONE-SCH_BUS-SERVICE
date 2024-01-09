package graduation.busstation.repository;

import graduation.busstation.entity.CarLicense;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarLicenseRepositoryTest {

    @Autowired
    CarLicenseRepository carLicenseRepository;

    @Test
    @Transactional
    public void testCarLicense(){
        CarLicense license = new CarLicense();
        license.setLicense("12가4519");
        CarLicense savedLicense = carLicenseRepository.save(license);

        CarLicense findCarLicense = carLicenseRepository.findById(savedLicense.getLicense()).get();

        Assertions.assertThat(findCarLicense.getLicense()).isEqualTo(savedLicense.getLicense());
        Assertions.assertThat(findCarLicense).isEqualTo(savedLicense);
    }

}