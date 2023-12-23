package graduation.busstation.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BusStation {


    @Id
    @GeneratedValue
    private Long id;

    private String busStationName;
    private String deviceMacAddress;
    private String stationStatus;
    private LocalDateTime dateTime;


}
