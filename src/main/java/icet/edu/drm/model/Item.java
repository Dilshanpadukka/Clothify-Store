package icet.edu.drm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String id;
    private String name;
    private String category;
    private String size;
    private String description;
    private String supId;
    private String qty;
    private String unitPrice;
}
