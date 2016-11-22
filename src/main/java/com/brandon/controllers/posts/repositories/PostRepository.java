package com.brandon.controllers.posts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.brandon.controllers.posts.entities.PostEntity;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends JpaRepository<PostEntity, Long> {
	Page<PostEntity> findBySubjectLike(Pageable pageable, String subject);

	Page<PostEntity> findByContentLike(Pageable pageable, String content);

	Page<PostEntity> findBySubjectLikeAndContentLike(Pageable pageable, String subject, String content);
}
