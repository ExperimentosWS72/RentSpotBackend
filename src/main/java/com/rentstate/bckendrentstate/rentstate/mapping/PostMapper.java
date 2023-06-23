package com.rentstate.bckendrentstate.rentstate.mapping;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.resource.request.PostRequest;
import com.rentstate.bckendrentstate.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class PostMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public Post toModel(PostRequest request) {

        return mapper.map(request, Post.class);
    }
    public PostRequest toRequest(Post post) {

        return mapper.map(post, PostRequest.class);
    }

}
