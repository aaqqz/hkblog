package com.hkblog.api.controller;

import com.hkblog.api.domain.Post;
import com.hkblog.api.request.PostCreate;
import com.hkblog.api.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, Long> post(@RequestBody @Valid @NotNull PostCreate request) {
        Long postId = postService.write(request);
        return Map.of("postId", postId);
    }

    @GetMapping("/posts/{postId}")
    public Post get(@PathVariable(name = "postId") Long id) {
        Post post = postService.get(id);
        return post;
    }

    @GetMapping("/posts/{postId}/rss")
    public Post getRss(@PathVariable(name = "postId") Long id) {
        Post post = postService.get(id);
        return post;
    }
}
