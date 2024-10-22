package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String supplierContactNumber;
    private String supplierItem;
    private String supplierCompany;
    private String supplierAddress;
    private String supplierEmailAddress;
    private String supplierPostalCode;
}
