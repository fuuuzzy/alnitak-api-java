package com.ztingfg.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PaginationResult<T> extends Pagination {

    private List<T> data;

    private Boolean hasMore;

    private Long total;

    public static <T> PaginationResult<T> from(List<T> data, int currentPage, int pageSize, boolean hasMore) {
        PaginationResult<T> result = new PaginationResult<>();
        result.setPage(currentPage);
        result.setPageSize(pageSize);
        result.setHasMore(hasMore);
        result.setData(data);
        return result;
    }

    public static <T> PaginationResult<T> from(List<T> data, boolean hasMore) {
        PaginationResult<T> result = new PaginationResult<>();
        result.setHasMore(hasMore);
        result.setData(data);
        return result;
    }

    public static <T> PaginationResult<T> from(List<T> data, Long total) {
        PaginationResult<T> result = new PaginationResult<>();
        result.setHasMore(false);
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public static <T> PaginationResult<T> from(List<T> data, boolean hasMore, Long total) {
        PaginationResult<T> result = new PaginationResult<>();
        result.setHasMore(hasMore);
        result.setData(data);
        result.setTotal(total);
        return result;
    }
}
