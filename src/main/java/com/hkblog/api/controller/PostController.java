package com.hkblog.api.controller;

import com.hkblog.api.exception.InvalidRequest;
import com.hkblog.api.request.PostCreate;
import com.hkblog.api.request.PostEdit;
import com.hkblog.api.request.PostSearch;
import com.hkblog.api.response.PostResponse;
import com.hkblog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Map<String, Long> post(@RequestBody @Valid PostCreate request) {
        request.validate();

        Long postId = postService.write(request);
        return Map.of("postId", postId);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
