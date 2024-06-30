package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Order {

    private String id;
    private String employeeId;
    private String ItemId;
    private String name;
    private Double unitPrice;
    private Integer quantity;
    private Double totalCharge;

}
