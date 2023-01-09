package com.hkblog.api.repository;

import com.hkblog.api.domain.Post;
import com.hkblog.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
