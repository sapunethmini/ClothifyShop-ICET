package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Supplier {

    private String id;
    private String name;
    private String company;
    private String email;
    private String item;


}
