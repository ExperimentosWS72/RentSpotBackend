package com.rentstate.bckendrentstate.rentstate.resource.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeRequest {
        private Long authorId;

        private Long recipientId;

        private String content;
}
