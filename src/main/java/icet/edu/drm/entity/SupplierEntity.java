package icet.edu.drm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "supplier")
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    private String id;
    private String name;
    private String companyName;
    private String contact;
    private String city;
    private String postalCode;
    private String comEmail;
    private String address;
}
