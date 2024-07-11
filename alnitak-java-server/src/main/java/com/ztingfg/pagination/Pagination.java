package com.ztingfg.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    protected int page = 1;

    protected int pageSize = 20;

    protected int limit;

    protected int offset;

    @JsonIgnore
    public int getLimit() {
        return pageSize;
    }

    @JsonIgnore
    public int getOffset() {
        return (pageSize > 0 ? pageSize : 20) * (page > 0 ? (page - 1) : 0);
    }
}
