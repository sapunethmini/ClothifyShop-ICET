package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

 @AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "supplier")
 public class SupplierEntity{
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "item")
    private String item;



}
