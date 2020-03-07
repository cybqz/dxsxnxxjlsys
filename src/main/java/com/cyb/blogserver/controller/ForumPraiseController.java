package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.forum.common.R;
import com.cyb.forum.domain.ForumDiscuss;
import com.cyb.forum.domain.ForumPraise;
import com.cyb.forum.service.ForumDiscussService;
import com.cyb.forum.service.ForumPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value="/forumpraise")
public class ForumPraiseController extends BaseController {

    @Autowired
    private ForumPraiseService forumPraiseService;

    @RequestMapping(value="/praise")
    @ResponseBody
    public Tips praise (ForumPraise forumPraise) {
        Tips tips = new Tips("点赞失败", true, false);
        super.validLogined();
        if(user != null){
            forumPraise.setId(MyUtils.getPrimaryKey());
            forumPraise.setUserId(user.getId());
            forumPraise.setCreateDateTime(new Date());
            R r = forumPraiseService.insert(forumPraise);
            tips.setMsg(r.getMsg());
            tips.setValidate(r.isSuccess());
        }else{
            tips.setMsg("登陆先");
        }
        return tips;
    }

    @RequestMapping(value="/detail")
    @ResponseBody
    public Tips detail (String id) {
        Tips tips = new Tips("点赞失败", true, false);
        forumPraiseService.selectByPrimaryKey(id);
        return tips;
    }
}
