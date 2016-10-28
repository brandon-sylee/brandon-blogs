package com.brandon.controllers.posts.apis;

import com.brandon.controllers.ApiResults;
import com.brandon.controllers.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

    @GetMapping
    public ApiResults<?> list(Pageable pageable) {
        return new ApiResults<>(postService.list(pageable));
    }

    @GetMapping("{id:\\d+}")
    public ApiResults<?> read(@PathVariable Long id) {
        return new ApiResults<>(postService.read(id));
    }
}
