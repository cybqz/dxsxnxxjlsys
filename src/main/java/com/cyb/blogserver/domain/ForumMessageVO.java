package com.cyb.blogserver.domain;

import com.cyb.forum.domain.ForumMessage;
import lombok.Data;

@Data
public class ForumMessageVO extends ForumMessage {

    private String name;

    private String userImg;
}
