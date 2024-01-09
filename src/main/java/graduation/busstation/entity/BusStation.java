package graduation.busstation.entity;


import graduation.busstation.Status.StationStatus;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private StationStatus stationStatus;
    @Column(name="ARRIVED_DATE_TIME")
    private LocalDateTime arrivedDateTime;
    @Column(name="DEPARTED_DATE_TIME")
    private LocalDateTime departedDateTime;

}
