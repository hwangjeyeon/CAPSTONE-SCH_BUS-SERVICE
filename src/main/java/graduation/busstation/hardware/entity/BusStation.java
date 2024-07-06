package graduation.busstation.hardware.entity;


import graduation.busstation.hardware.enums.StationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "BUS_STATION")
public class BusStation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setArrivedAndStatus(LocalDateTime arrivedDateTime, StationStatus stationStatus){
        this.arrivedDateTime = arrivedDateTime;
        this.stationStatus = stationStatus;
    }

    public void setDepartedAndStatus(LocalDateTime departedDateTime, StationStatus stationStatus){
        this.departedDateTime = departedDateTime;
        this.stationStatus = stationStatus;
    }

}
