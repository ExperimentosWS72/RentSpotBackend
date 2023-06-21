package com.rentstate.bckendrentstate.rentstate.domain.persistence;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
