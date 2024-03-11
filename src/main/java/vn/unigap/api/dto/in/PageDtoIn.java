package vn.unigap.api.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PageDtoIn {
    @NotNull
    @Min(value = 1)
    private Integer page = 1;

    @NotNull
    @Min(value = 1)
    @Max(value = 500)
    private Integer pageSize = 10;
}
