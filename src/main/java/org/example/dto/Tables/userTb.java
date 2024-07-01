package org.example.dto.Tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class userTb {


    private String id;
    private String name;
    private String email;
    private String company;
    private String password;

}
