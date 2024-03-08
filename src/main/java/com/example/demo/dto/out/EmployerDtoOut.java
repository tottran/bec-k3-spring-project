package com.example.demo.dto.out;

import com.example.demo.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Integer id;
    private String email;
    private String name;
    private String province;
    private String description;

    public static EmployerDtoOut from(Employer employer) {
        return EmployerDtoOut.builder()
                .id(employer.getId())
                .email(employer.getEmail())
                .name(employer.getName())
                .province(employer.getProvince())
                .description(employer.getDescription())
                .build();
    }
}
