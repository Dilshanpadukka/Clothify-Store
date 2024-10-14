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
@Entity(name = "item")
@Table(name = "item")
public class ItemEntity {
    @Id
    private String id;
    private String name;
    private String category;
    private String size;
    private String description;
    private String supId;
    private String qty;
    private String unitPrice;
}
