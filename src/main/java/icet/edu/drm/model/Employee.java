package icet.edu.drm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String id;
    private String name;
    private String contact;
    private String nic;
    private String address;
    private String email;
    private String password;

}
