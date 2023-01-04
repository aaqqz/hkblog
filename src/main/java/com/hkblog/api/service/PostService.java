package com.hkblog.api.service;

import com.hkblog.api.domain.Post;
import com.hkblog.api.repository.PostRepository;
import com.hkblog.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long write(PostCreate postCreate) {
        // postCreate -> Entity
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
        return post.getId();
    }
}
