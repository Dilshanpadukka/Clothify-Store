package icet.edu.drm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "employee")
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private String contact;
    private String nic;
    private String address;
    private String email;
    private String password;

    public EmployeeEntity(String id, String address, String contact, String email, String name, String nic, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.nic = nic;
        this.address = address;
        this.email = email;
        this.password = password;
    }


}
