package graduation.busstation.hardware.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name="CAR_LICENSE")
@Getter @Setter
@Entity
public class CarLicense {

    @Id
    private String License;



}
