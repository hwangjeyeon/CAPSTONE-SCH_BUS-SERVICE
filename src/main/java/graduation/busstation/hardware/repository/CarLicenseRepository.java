package graduation.busstation.hardware.repository;

import graduation.busstation.hardware.entity.CarLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLicenseRepository extends JpaRepository<CarLicense, String> {

}
