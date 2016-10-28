package com.brandon.controllers.posts.repositories;

import com.brandon.controllers.posts.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
