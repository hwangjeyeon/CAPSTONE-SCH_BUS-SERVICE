package graduation.busstation.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BusInfo {

    @Id
    @GeneratedValue
    private Long id;

    private int bus_count;

}
