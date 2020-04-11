package com.cyb.cleg.controller;

import com.cyb.authority.base.BaseController;
import com.cyb.cleg.domain.Team;
import com.cyb.cleg.domain.TeamMember;
import com.cyb.cleg.service.TeamMemberServices;
import com.cyb.cleg.service.TeamServices;
import com.cyb.cleg.utils.MatrixToImageWriter;
import com.cyb.cleg.vo.TeamVO;
import com.cyb.common.pagenation.Pagenation;
import com.cyb.common.tips.Tips;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *组队控制层
 */
@Controller
@RequestMapping(value="/team")
public class TeamController extends BaseController {

	@Autowired
	private TeamServices teamServices;

	@Autowired
	private TeamMemberServices teamMemberServices;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (String name) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("添加组队失败");
			if(StringUtils.isBlank(name) || name.length() >20){
				name = currentLoginedUser.getUserName();
			}
			Team team = new Team(null, currentLoginedUser.getId(), name, null);
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
			List<Team> list =  teamServices.selectSelective(null);
			List<TeamVO> teamVOList = null;
			if(null != list && !list.isEmpty()){
				teamVOList = new ArrayList<>(list.size());
				for(Team t : list){
					TeamVO vo = new TeamVO();
					BeanUtils.copyProperties(t, vo);
					boolean isMyCreated = t.getUserId().equals(currentLoginedUser.getId());
					vo.setMyCreated(isMyCreated);
					if(!isMyCreated){
						TeamMember teamMember = new TeamMember(null, t.getId(), currentLoginedUser.getId(), null, null);
						List<TeamMember> teamMemberList = teamMemberServices.selectSelective(teamMember);
						if(teamMemberList != null && !teamMemberList.isEmpty()){
							vo.setJoined(true);
						}
					}
					vo.setJoined(isMyCreated);
					teamVOList.add(vo);
				}
				pagenation.setDataCount(list.size());
			}else{
				pagenation.setDataCount(0);
			}
			tips.setPagenation(pagenation);
			tips.setData(teamVOList);
		}
		return tips;
	}

	@RequestMapping(value="/mecreatepage")
	@ResponseBody
	public Tips mecreatepage (Team team, Pagenation pagenation) {
		super.validLogined();
		if(isLogined){
			tips = new Tips("查询成功", true, true);
			team.setUserId(currentLoginedUser.getId());
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
						inputStream = MatrixToImageWriter.creatImage(team.getId());
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
