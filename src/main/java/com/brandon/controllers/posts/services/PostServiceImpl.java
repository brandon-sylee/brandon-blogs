package com.brandon.controllers.posts.services;

import com.brandon.controllers.posts.entities.PostEntity;
import com.brandon.controllers.posts.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    @Transactional(readOnly = true)
    public Page<PostEntity> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PostEntity read(Long id) {
        return repository.findOne(id);
    }

    public PostEntity write(PostEntity entity) {
        return repository.save(entity);
    }

    public PostEntity update(Long id, PostEntity entity) {
        PostEntity target = read(id);
        target.setSubject(entity.getSubject());
        target.setContent(entity.getContent());
        return target;
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
