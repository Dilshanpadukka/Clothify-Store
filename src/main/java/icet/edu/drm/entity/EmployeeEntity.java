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
@Entity(name = "Employee")
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private String contact;
    private String nic;
    private String address;
    private String email;
    private String password;

}
