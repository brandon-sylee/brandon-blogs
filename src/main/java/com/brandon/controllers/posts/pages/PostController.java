package com.brandon.controllers.posts.pages;

import com.brandon.controllers.posts.entities.PostEntity;
import com.brandon.controllers.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by brandon Lee on 2016-10-26.
 */
@Controller
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final String prefix = "post";
    private final String redirect = "redirect:";
    private final String POST_REDIRECT_LIST = String.join("/", redirect, "post");
    private final String POST_FORM = String.join("/", prefix, "form");
    private final String POST_LIST = String.join("/", prefix, "list");
    private final PostService postService;

    @GetMapping
    public String list() {
        return POST_LIST;
    }

    @GetMapping("form")
    public String form() {
        return POST_FORM;
    }

    @PostMapping
    public String save(@Valid PostEntity entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return POST_FORM;
        postService.write(entity);
        return POST_REDIRECT_LIST;
    }

    @PatchMapping("{id:\\d+}")
    @PutMapping("{id:\\d+}")
    public String update(@PathVariable Long id, @Valid PostEntity entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return POST_FORM;
        postService.update(id, entity);
        return POST_REDIRECT_LIST;
    }

    @DeleteMapping("{id:\\d+}")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return POST_REDIRECT_LIST;
    }
}
