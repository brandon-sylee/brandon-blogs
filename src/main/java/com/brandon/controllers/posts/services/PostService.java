package com.brandon.controllers.posts.services;

import com.brandon.controllers.posts.beans.PostSearching;
import com.brandon.controllers.posts.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by brandon Lee on 2016-10-28.
 */
public interface PostService {

    Page<PostEntity> list(Pageable pageable);

    Page<PostEntity> search(Pageable pageable, PostSearching postSearching);

    PostEntity read(Long id);

    PostEntity write(PostEntity entity);

    PostEntity update(Long id, PostEntity entity);

    void delete(Long id);
}
