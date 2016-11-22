package com.brandon.controllers.posts.services;

import com.brandon.controllers.posts.beans.PostSearching;
import com.brandon.controllers.posts.entities.PostEntity;
import com.brandon.controllers.posts.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

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
    private final Logger logger = getLogger(getClass());
    @Override
    public Page<PostEntity> search(Pageable pageable, PostSearching postSearching) {
        logger.warn("{}", postSearching);

        switch (postSearching.getFields()) {
            case C:
                return repository.findByContentLike(pageable, postSearching.getKeyword());
            case T:
                return repository.findBySubjectLike(pageable, postSearching.getKeyword());
            case TC:
                return repository.findBySubjectLikeAndContentLike(pageable, postSearching.getKeyword(), postSearching.getKeyword());
            default:
                return null;
        }
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
