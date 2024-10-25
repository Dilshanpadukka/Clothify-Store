package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String orderId;
    private String empId;
    private String custId;
    private LocalDate date;
    private LocalTime time;
    private String netTotal;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetailsEntity> orderDetails;
}
