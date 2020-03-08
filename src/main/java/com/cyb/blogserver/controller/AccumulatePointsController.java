package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.AccumulatePoints;
import com.cyb.blogserver.service.AccumulatePointsServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.forum.domain.ForumDiscuss;
import com.cyb.forum.service.ForumDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value="/accumulatepoints")
public class AccumulatePointsController extends BaseController {

    @Autowired
    private AccumulatePointsServices accumulatePointsServices;

    @RequestMapping(value="/add")
    @ResponseBody
    public Tips add (AccumulatePoints accumulatePoints) {
        Tips tips = new Tips("积分新增失败", true, false);
        super.validLogined();
        if(user != null){
            accumulatePointsServices.addPoints(user.getId(), Constant.PARAMES_NAME_SIGNIN);
            tips = new Tips("积分新增成功", true, true);
        }
        return tips;
    }

    @RequestMapping(value="/selectAccumulatePoints")
    @ResponseBody
    public Tips selectAccumulatePoints () {
        Tips tips = new Tips("查询积分失败", true, false);
        super.validLogined();
        if(user != null){
            AccumulatePoints params = new AccumulatePoints(null, user.getId(), null, null, null);
            AccumulatePoints accumulatePoints = accumulatePointsServices.selectOneSelective(params);
            tips = new Tips("查询积分成功", true, true);
            tips.setData(accumulatePoints);
        }
        return tips;
    }
}
