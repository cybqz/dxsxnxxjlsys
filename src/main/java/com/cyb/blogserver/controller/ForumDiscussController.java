package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.forum.domain.ForumDiscuss;
import com.cyb.forum.service.ForumDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;

@Controller
@RequestMapping(value="/forumdiscuss")
public class ForumDiscussController extends BaseController {

    @Autowired
    private ForumDiscussService forumDiscussService;

    @RequestMapping(value="/add")
    @ResponseBody
    public Tips add (ForumDiscuss forumDiscuss) {
        Tips tips = new Tips("true", true);
        forumDiscuss.setId(MyUtils.getPrimaryKey());
        forumDiscuss.setUserId("1111");
        forumDiscuss.setContent("c");
        forumDiscuss.setImg("1");
        forumDiscuss.setCreateDateTime(new Date());
        forumDiscussService.insert(forumDiscuss);
        return tips;
    }
}
