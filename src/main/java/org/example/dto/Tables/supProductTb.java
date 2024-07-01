package org.example.dto.Tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class supProductTb {

    private String id;
    private String ProductID;
    private String category;
    private Integer Qty;
    private Double totalCharge;


}
