package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.AccumulatePoints;
import com.cyb.blogserver.vo.AccumulatePointsVO;
import com.cyb.blogserver.service.AccumulatePointsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/accumulatepoints")
public class AccumulatePointsController extends BaseController {

    @Autowired
    private AccumulatePointsServices accumulatePointsServices;

    @RequestMapping(value="/add")
    @ResponseBody
    public Tips add (AccumulatePoints accumulatePoints) {
        super.validLogined();
        if(isLogined){
            tips.setMsg("积分新增失败");
            accumulatePointsServices.addPoints(currentLoginedUser.getId(), Constant.PARAMES_NAME_SIGNIN);
            tips = new Tips("积分新增成功", true, true);
        }
        return tips;
    }

    @RequestMapping(value="/select")
    @ResponseBody
    public Tips selectAccumulatePoints () {
        super.validLogined();
        if(isLogined){
            tips.setMsg("查询积分失败");
            AccumulatePoints params = new AccumulatePoints(null, currentLoginedUser.getId(), null, null, null);
            AccumulatePoints accumulatePoints = accumulatePointsServices.selectOneSelective(params);
            tips = new Tips("查询积分成功", true, true);
            tips.setData(accumulatePoints);
        }
        return tips;
    }

    /**
     * 查询全网前10
     * @return
     */
    @RequestMapping(value="/selectSystemTopTen")
    @ResponseBody
    public Tips selectSystemTopTen () {
        super.validLogined();
        if(isLogined){
            tips.setMsg("查询积分失败");
            List<AccumulatePointsVO> accumulatePoints = accumulatePointsServices.selectSystemTopTen();
            tips = new Tips("查询积分成功", true, true);
            tips.setData(accumulatePoints);
        }
        return tips;
    }

    /**
     * 查询好友前10
     * @return
     */
    @RequestMapping(value="/selectFriendsTopTen")
    @ResponseBody
    public Tips selectFriendsTopTen () {
        super.validLogined();
        if(isLogined){
            tips.setMsg("查询积分失败");
            AccumulatePoints params = new AccumulatePoints(null, currentLoginedUser.getId(), null, null, null);
            List<AccumulatePointsVO> accumulatePoints = accumulatePointsServices.selectFriendsTopTen(currentLoginedUser.getId());
            tips = new Tips("查询积分成功", true, true);
            tips.setData(accumulatePoints);
        }
        return tips;
    }


}
