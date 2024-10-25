package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeeNic;
    private String employeeAddress;
    private String employeeEmailAddress;
    private String contactNumber;
    private String password;
}

