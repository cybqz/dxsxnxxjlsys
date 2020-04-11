package com.cyb.cleg.controller;

import com.cyb.authority.base.BaseController;
import com.cyb.authority.domain.User;
import com.cyb.authority.service.UserService;
import com.cyb.cleg.vo.ForumMessageVO;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.common.pagenation.Pagenation;
import com.cyb.common.tips.Tips;
import com.cyb.forum.domain.ForumMessage;
import com.cyb.forum.domain.ForumPraise;
import com.cyb.forum.service.ForumMessageService;
import com.cyb.forum.service.ForumPraiseService;
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
    private ForumPraiseService forumPraiseService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add")
    @ResponseBody
    public Tips add (ForumMessage forumMessage) {
        super.validLogined();
        if(isLogined){

            if(StringUtils.isNotEmpty(forumMessage.getContent())){
                forumMessage.setId(MyUtils.getPrimaryKey());
                forumMessage.setUserId(currentLoginedUser.getId());
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

    @RequestMapping(value="/delete")
    @ResponseBody
    public Tips delete (String id) {
        super.validLogined();
        if(isLogined) {
            if(StringUtils.isNotEmpty(id)){
                ForumMessage message = forumMessageService.selectByPrimaryKey(id);
                if(message != null){
                    if(message.getUserId().equals(currentLoginedUser.getId())){
                        forumMessageService.deleteByPrimaryKey(id);
                        tips.setValidate(true);
                        tips.setMsg("删除成功");
                    }else {
                        tips.setMsg("删除权限不足");
                    }
                }else{
                    tips.setMsg("对象不存在");
                }
            }else{
                tips.setMsg("ID不能为空");
            }
        }
        return tips;
    }

    @RequestMapping(value="/page")
    @ResponseBody
    public Tips page (ForumMessage forumMessage, Pagenation pagenation) {
        super.validLogined();
        if(isLogined){
            tips = new Tips("查询成功", true, true);
            List<ForumMessageVO> listVO = null;
            int count = forumMessageService.countByForumMessage(forumMessage);
            List<ForumMessage> list =  forumMessageService.selectSelective(forumMessage, pagenation.getPageIndex(), pagenation.getPageSize());
            if(null != list && !list.isEmpty()){

                listVO = new ArrayList<>(list.size());
                setVO(list, listVO);
            }
            pagenation.setDataCount(count);
            tips.setPagenation(pagenation);
            tips.setData(listVO);
        }
        return tips;
    }

    @RequestMapping(value="/mypage")
    @ResponseBody
    public Tips mypage (ForumMessage forumMessage, Pagenation pagenation) {
        super.validLogined();
        if(isLogined){
            tips = new Tips("查询成功", true, true);
            List<ForumMessageVO> listVO = null;
            forumMessage.setUserId(currentLoginedUser.getId());
            int count = forumMessageService.countByForumMessage(forumMessage);
            List<ForumMessage> list =  forumMessageService.selectSelective(forumMessage, pagenation.getPageIndex(), pagenation.getPageSize());
            if(null != list && !list.isEmpty()){

                listVO = new ArrayList<>(list.size());
                setVO(list, listVO);
            }
            pagenation.setDataCount(count);
            tips.setPagenation(pagenation);
            tips.setData(listVO);
        }
        return tips;
    }

    private void setVO(List<ForumMessage> list, List<ForumMessageVO> listVO){

        for(ForumMessage message : list){
            ForumMessageVO vo = new ForumMessageVO();
            BeanUtils.copyProperties(message, vo);
            User user = userService.selectByPrimaryKey(message.getUserId());
            if(null != user){
                vo.setName(user.getUserName());
                vo.setUserImg(user.getImage());
            }
            ForumPraise praise = new ForumPraise();
            praise.setUserId(currentLoginedUser.getId());
            praise.setMessageId(message.getId());
            List<ForumPraise> parseList = forumPraiseService.selectSelective(praise);
            if(parseList != null && !parseList.isEmpty()){
                vo.setPraise(true);
            }
            listVO.add(vo);
        }
    }
}
