package graduation.busstation.repository;

import graduation.busstation.entity.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<BusStation, Long> {
}
