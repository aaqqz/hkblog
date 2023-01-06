package com.hkblog.api.controller;

import com.hkblog.api.request.PostCreate;
import com.hkblog.api.response.PostResponse;
import com.hkblog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList() {
        return postService.getList();
    }
}
