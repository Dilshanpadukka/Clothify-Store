package icet.edu.drm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "customer")
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    private String title;
    private String name;
    private String contact;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String email;
}
