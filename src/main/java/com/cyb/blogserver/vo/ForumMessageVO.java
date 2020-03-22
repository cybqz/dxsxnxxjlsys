package com.cyb.blogserver.vo;

import com.cyb.forum.domain.ForumMessage;
import lombok.Data;

@Data
public class ForumMessageVO extends ForumMessage {

    private String name;

    private boolean isPraise = false;

    private String userImg;
}
