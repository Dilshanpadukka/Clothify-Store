package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id

    private String itemId;
    private String name;
    private String category;
    private String size;
    private String supId;
    private Double price;
    private Integer qty;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderDetailsEntity> orderDetails;
}
