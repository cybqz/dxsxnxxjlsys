package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Team;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.TeamServices;
import com.cyb.blogserver.utils.MatrixToImageWriter;
import com.cyb.blogserver.utils.UserValidate;
import com.cyb.blogserver.vo.ForumMessageVO;
import com.cyb.forum.domain.ForumMessage;
import com.cyb.forum.domain.ForumPraise;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *组队控制层
 */
@Controller
@RequestMapping(value="/team")
public class TeamController extends BaseController {

	@Autowired
	private TeamServices teamServices;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (String name) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("添加组队失败");
			if(StringUtils.isBlank(name) || name.length() >20){
				name = user.getUserName();
			}
			Team team = new Team(null, user.getId(), name, null);
			int result = teamServices.insert(team);
			if(result > 0){
				tips = new Tips("添加组队成功！", true, true);
			}
		}
		return tips;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Tips delete (String id) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("删除组队失败");
			int result = teamServices.deleteByPrimaryKey(id);
			if(result > 0){
				tips = new Tips("删除组队成功！", true, true);
			}
		}
		return tips;
	}

	@RequestMapping(value="/page")
	@ResponseBody
	public Tips page (Team team, Pagenation pagenation) {
		super.validLogined();
		if(isLogined){
			tips = new Tips("查询成功", true, true);
			team.setUserId(user.getId());
			List<Team> list =  teamServices.selectSelective(team);
			if(null != list && !list.isEmpty()){
				pagenation.setDataCount(list.size());
			}else{
				pagenation.setDataCount(0);
			}
			tips.setPagenation(pagenation);
			tips.setData(list);
		}
		return tips;
	}

	@RequestMapping(value="/getMatrixImage")
	@ResponseBody
	public Tips getMatrixImage (String id) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("获取二维码图片失败");
			if(StringUtils.isBlank(id)){
				tips.setMsg("组id不能为空");
			}else{
				Team team = teamServices.selectByPrimaryKey(id);
				if(null == team){
					tips.setMsg("组不存在");
				}else{
					InputStream inputStream = null;
					BASE64Encoder encoder = new BASE64Encoder();
					try {
						//这里放的是需要放入二维码的信息
						inputStream = MatrixToImageWriter.creatImage("http://192.168.124.6:9090/teammember/add?teamId=7d1a75ab-9c5b-4a77-804a-546e753a3c5f");
						byte[] bytes = new byte[inputStream.available()];
						inputStream.read(bytes);
						tips = new Tips("获取二维码成功", true, encoder.encode(bytes));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(null != inputStream){
							try {
								inputStream.close();
							}catch (Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return tips;
	}
}
