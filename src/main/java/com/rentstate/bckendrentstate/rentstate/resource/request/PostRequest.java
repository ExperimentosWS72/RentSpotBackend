package com.rentstate.bckendrentstate.rentstate.resource.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

        private String title;

        private String description;

        private String characteristics;

        private String location;

        private Float price;

        private String imgUrl;

        private String category;

        private Long author_id;

}
