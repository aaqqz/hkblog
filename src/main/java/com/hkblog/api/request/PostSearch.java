package com.hkblog.api.request;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSearch {

    private int page;
    private int size;

    @Builder
    public PostSearch(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
