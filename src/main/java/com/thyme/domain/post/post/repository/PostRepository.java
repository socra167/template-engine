package com.thyme.domain.post.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thyme.domain.post.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
