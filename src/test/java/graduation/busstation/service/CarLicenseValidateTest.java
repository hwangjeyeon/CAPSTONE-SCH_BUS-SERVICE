package graduation.busstation.service;

import graduation.busstation.entity.BusStation;
import graduation.busstation.entity.CarLicense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarLicenseValidateTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CarLicenseValidate carLicenseValidate;

    @BeforeEach
    public void before(){
        CarLicense carLicense = new CarLicense();
        carLicense.setLicense("12가4519");
        em.persist(carLicense);
        carLicenseValidate = new CarLicenseValidate(em);
    }

    @Test
    public void validateCarLicense(){
        boolean test1 = carLicenseValidate.validateCarLicense("12가4519");
        boolean test2 = carLicenseValidate.validateCarLicense("17나9284");

        assertThat(test1).isTrue();
        assertThat(test2).isFalse();
    }


}