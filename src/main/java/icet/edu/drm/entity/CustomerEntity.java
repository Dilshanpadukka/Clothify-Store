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
    private String name;
    private String contact;
    private String address;
    private String email;
}
