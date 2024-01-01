package graduation.busstation.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BusStation {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name="BUS_STATION_NAME")
    private String busStationName;
    @Column(name="DEVICE_MAC_ADDRESS")
    private String deviceMacAddress;
    @Column(name="STATION_STATUS")
    private String stationStatus;
    @Column(name="DATE_TIME")
    @LastModifiedDate
    private LocalDateTime dateTime;

}
