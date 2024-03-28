package vn.unigap.api.dto.out;

import vn.unigap.api.entity.jpa.Employer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Long id;
    private String email;
    private String name;
    private Integer provinceId;
    private String provinceName;
    private String description;

    public static EmployerDtoOut from(Employer employer) {
        return EmployerDtoOut.builder()
                .id(employer.getId())
                .email(employer.getEmail())
                .name(employer.getName())
                .provinceId(employer.getProvince())
                .description(employer.getDescription())
                .build();
    }
}
