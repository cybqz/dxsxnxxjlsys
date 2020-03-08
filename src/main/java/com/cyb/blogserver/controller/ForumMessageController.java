package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.ForumMessageVO;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.UserServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.forum.domain.ForumMessage;
import com.cyb.forum.service.ForumMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/forummessabe")
public class ForumMessageController extends BaseController {

    @Autowired
    private ForumMessageService forumMessageService;

    @Autowired
    private UserServices userServices;

    @RequestMapping(value="/add")
    @ResponseBody
    public Tips add (ForumMessage forumMessage) {
        Tips tips = new Tips("发布失败", true, false);
        super.validLogined();
        if(user != null){

            if(StringUtils.isNotEmpty(forumMessage.getContent())){
                forumMessage.setId(MyUtils.getPrimaryKey());
                forumMessage.setUserId(user.getId());
                forumMessage.setCollectCount(0);
                forumMessage.setDiscussCount(0);
                forumMessage.setForwardCount(0);
                forumMessage.setPraiseCount(0);
                forumMessage.setCreateDateTime(new Date());
                int add = forumMessageService.insert(forumMessage);
                if(add > 0){
                    tips = new Tips("发布成功", true, true);
                }
            }else{
                tips.setMsg("内容不能为空");
            }
        }
        return tips;
    }

    @RequestMapping(value="/page")
    @ResponseBody
    public Tips page (ForumMessage forumMessage, Pagenation pagenation) {
        Tips tips = new Tips("查询失败", true, false);
        super.validLogined();
        if(user != null){
            tips = new Tips("查询成功", true, true);
            List<ForumMessage> listVO = null;
            int count = forumMessageService.countByForumMessage(forumMessage);
            List<ForumMessage> list =  forumMessageService.selectSelective(forumMessage, pagenation.getPageIndex(), pagenation.getPageSize());
            if(null != list && !list.isEmpty()){

                listVO = new ArrayList<>(list.size());
                for(ForumMessage message : list){
                    ForumMessageVO vo = new ForumMessageVO();
                    BeanUtils.copyProperties(message, vo);
                    User user = userServices.selectByPrimaryKey(message.getUserId());
                    if(null != user){
                        vo.setName(user.getUserName());
                        vo.setUserImg(user.getImage());
                    }
                    listVO.add(vo);
                }
            }

            pagenation.setDataCount(count);
            tips.setPagenation(pagenation);
            tips.setData(listVO);
        }
        return tips;
    }
}
