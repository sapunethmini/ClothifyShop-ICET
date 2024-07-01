package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class OrderEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "empID")
    private String employeeId;

    @Column(name = "itemID")
    private String itemId;

    @Column(name = "name")
    private String name;

    @Column(name = "Uprice")
    private Double unitPrice;

    @Column(name = "Qty")
    private Integer quantity;

    @Column(name = "charge")
    private Double totalCharge;
}