package com.brandon.controllers.posts.apis;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.brandon.controllers.ApiResults;
import com.brandon.controllers.posts.beans.PostSearching;
import com.brandon.controllers.posts.services.PostService;

import lombok.RequiredArgsConstructor;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostRestController {
	private final Logger logger = getLogger(getClass());
	private final PostService postService;

	@GetMapping
	public ApiResults<?> list(Pageable pageable) {
		return new ApiResults<>(postService.list(pageable));
	}

	@GetMapping("{id:\\d+}")
	public ApiResults<?> read(@PathVariable Long id) {
		return new ApiResults<>(postService.read(id));
	}

	@PostMapping
	public ApiResults<?> search(Pageable pageable,
		@RequestBody(required = false) @Valid PostSearching postSearching,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ApiResults<>(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
		}
		return Optional.ofNullable(postSearching).map(x -> new ApiResults<>(postService.search(pageable, postSearching))).orElse(new ApiResults<>(new Exception("Keyword dose't empty")));
	}

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public ApiResults<?> exceptionHandler(Exception e) {
		logger.warn("Api Call Exception ", e);
		return new ApiResults<>(e);
	}
}
