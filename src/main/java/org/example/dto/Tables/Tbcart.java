package org.example.dto.Tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Tbcart {
        private String itemCode;
        private String name;
        private Integer qty;
        private Double unitPrice;
        private Double total;

}
