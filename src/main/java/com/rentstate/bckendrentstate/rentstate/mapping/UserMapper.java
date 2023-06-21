package com.rentstate.bckendrentstate.rentstate.mapping;

import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.resource.CreateUserResource;
import com.rentstate.bckendrentstate.rentstate.resource.UpdateUserResource;
import com.rentstate.bckendrentstate.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

public class UserMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }
    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }

}
