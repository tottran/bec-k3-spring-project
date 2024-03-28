package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class PageDtoOut<T> {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int pageSize = 10;
    @Builder.Default
    private Long totalElements = 0L;
    @Builder.Default
    private Long totalPages = 0L;
    @Builder.Default
    private List<T> data = new ArrayList<>();

    public static <T> PageDtoOut<T> from(int page, int pageSize,
                                         Long totalElements, List<T> data) {
        long totalPages = totalElements / pageSize;
        long remains = totalElements % pageSize;
        if(remains != 0) {
            totalPages++;
        }

        int currentPage = page / pageSize + 1;
        //        Long itemsInThisPage = currentPage == totalPages ? (remains > 0 ? remains : pageSize) : pageSize;

        return PageDtoOut.<T>builder()
                .page(currentPage)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .data(data)
                .build();
    }
}
