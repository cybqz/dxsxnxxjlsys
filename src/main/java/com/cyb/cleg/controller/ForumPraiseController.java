package com.cyb.cleg.controller;

import com.cyb.cleg.common.BaseController;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.common.R.R;
import com.cyb.forum.domain.ForumPraise;
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
        super.validLogined();
        if(isLogined){
            forumPraise.setId(MyUtils.getPrimaryKey());
            forumPraise.setUserId(currentLoginedUser.getId());
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
        super.validLogined();
        if(isLogined){
            ForumPraise forumPraise = forumPraiseService.selectByPrimaryKey(id);
            tips = new Tips("success", true, forumPraise);
        }
        return tips;
    }
}
