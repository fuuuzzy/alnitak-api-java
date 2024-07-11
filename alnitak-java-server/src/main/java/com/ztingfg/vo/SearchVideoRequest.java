package com.ztingfg.vo;

import com.ztingfg.pagination.Pagination;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchVideoRequest extends Pagination {

    private String keyWords;

}
