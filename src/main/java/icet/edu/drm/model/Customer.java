package icet.edu.drm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
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
