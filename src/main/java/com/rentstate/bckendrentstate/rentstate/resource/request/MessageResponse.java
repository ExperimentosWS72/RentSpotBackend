package com.rentstate.bckendrentstate.rentstate.resource.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {

    private Long id;
    private String content;
    private String authorName;
    private Long authorId;
}
