package com.rentstate.bckendrentstate.rentstate.mapping;

import com.rentstate.bckendrentstate.rentstate.resource.request.MessageResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("rentstateMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public PostMapper postMapper(){
        return new PostMapper();
    }

    @Bean
    public MessageMapper messageMapper(){
        return new MessageMapper();
    }
}