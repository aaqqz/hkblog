package com.hkblog.api.controller;

import com.hkblog.api.domain.Post;
import com.hkblog.api.request.PostCreate;
import com.hkblog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map post(@RequestBody @Valid @NotNull PostCreate request) {
        Long postId = postService.write(request);
        return Map.of("postId", postId);
    }

    @GetMapping("/posts")
    public String get() {
        return "Hello World";
    }
}
