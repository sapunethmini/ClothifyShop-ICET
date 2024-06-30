package org.example.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.entity.SupplierEntity;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product {

    private String id;
    private String name;
    private String size;
    private Double price;
    private Integer Qty;
    private String supplierid;


}
