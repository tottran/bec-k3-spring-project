package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {
    private Integer id;
    private String email;
    private String name;
    private String province;
    private String description;
}
