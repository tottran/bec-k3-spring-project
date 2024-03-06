package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponsePage<T> {
    private int page;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private List<T> data;

    public static <T> ApiResponsePage<T> from(int page,  int pageSize,
        Long totalElements, List<T> data) {
        long totalPages = totalElements / pageSize;
        long remains = totalElements % pageSize;
        if(remains != 0) {
            totalPages++;
        }

        int currentPage = page / pageSize + 1;
        //        Long itemsInThisPage = currentPage == totalPages ? (remains > 0 ? remains : pageSize) : pageSize;

        return ApiResponsePage.<T>builder()
                .page(currentPage)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .data(data)
                .build();
    }
}
