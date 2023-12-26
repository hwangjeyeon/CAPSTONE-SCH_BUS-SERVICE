package graduation.busstation.service;


import com.querydsl.jpa.impl.JPAQueryFactory;
import graduation.busstation.entity.CarLicense;
import graduation.busstation.entity.QCarLicense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static graduation.busstation.entity.QCarLicense.carLicense;

@Service
@RequiredArgsConstructor
public class CarLicenseValidate {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public boolean validateCarLicense(String license){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        CarLicense dbLicense = queryFactory
                .select(carLicense)
                .from(carLicense)
                .where(carLicense.License.eq(license))
                .fetchOne();
        if(dbLicense == null){
            return false;
        }
        return true;
    }



}
