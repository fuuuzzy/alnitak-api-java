package com.ztingfg.pagination;

import java.util.List;

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

    public static <T> PaginationResult<T> from(List<T> data, boolean hasMore, Long total) {
        PaginationResult<T> result = new PaginationResult<>();
        result.setHasMore(hasMore);
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
