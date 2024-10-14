package icet.edu.drm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private String id;
    private String name;
    private String companyName;
    private String contact;
    private String city;
    private String postalCode;
    private String comEmail;
    private String address;
}
