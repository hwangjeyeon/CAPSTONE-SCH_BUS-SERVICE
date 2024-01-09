package graduation.busstation.repository;

import graduation.busstation.entity.CarLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLicenseRepository extends JpaRepository<CarLicense, String> {

}
