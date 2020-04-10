package com.cyb.cleg.controller;

import com.cyb.cleg.common.BaseController;
import com.cyb.cleg.common.Constant;
import com.cyb.cleg.common.Pagenation;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.domain.ShareObject;
import com.cyb.cleg.service.ShareObjectServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *共享模块控制层
 */
@Controller
@RequestMapping(value="/shareobject")
public class ShareObjectController extends BaseController {

	@Autowired
	private ShareObjectServices shareObjectServices;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (ShareObject shareObject) {
		super.validLogined();
		if(isLogined) {
			shareObject.setHot(0);
			shareObject.setUserId(currentLoginedUser.getId());
			int add = shareObjectServices.insert(shareObject);
			if(add > 0){
				tips = new Tips("添加成功！", true, true);
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

				ShareObject object = shareObjectServices.selectByPrimaryKey(id);
				if(null != object){
					if(object.getUserId().equals(currentLoginedUser.getId())){
						shareObjectServices.deleteByPrimaryKey(id);
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

	@RequestMapping(value="/admindelete")
	@ResponseBody
	public Tips adminDelete (String id) {
		super.validateAll(Constant.ROLE_ADMIN, null);
		if(isLogined) {
			if(StringUtils.isNotEmpty(id)){

				ShareObject object = shareObjectServices.selectByPrimaryKey(id);
				if(null != object){
					shareObjectServices.deleteByPrimaryKey(id);
					tips.setValidate(true);
					tips.setMsg("删除成功");
				}else{
					tips.setMsg("对象不存在");
				}
			}else{
				tips.setMsg("ID不能为空");
			}
		}
		return tips;
	}

	@RequestMapping(value="/adminpage")
	@ResponseBody
	public Tips adminPage (ShareObject usedBook, Pagenation pagenation) {
		super.validateAll(Constant.ROLE_ADMIN, null);
		if(isLogined) {
			List<ShareObject> list = shareObjectServices.selectSelective(usedBook, pagenation);
			tips = new Tips("查询成功！", true, true);
			tips.setData(list);
			tips.setPagenation(pagenation);
		}
		return tips;
	}

	@RequestMapping(value="/page")
	@ResponseBody
	public Tips page (ShareObject usedBook, Pagenation pagenation) {
		super.validLogined();
		if(isLogined) {
			List<ShareObject> list = shareObjectServices.selectSelective(usedBook, pagenation);
			tips = new Tips("查询成功！", true, true);
			tips.setData(list);
			tips.setPagenation(pagenation);
		}
		return tips;
	}

	@RequestMapping(value="/mypage")
	@ResponseBody
	public Tips myPage (ShareObject usedBook, Pagenation pagenation) {
		super.validLogined();
		if(isLogined) {
			usedBook.setUserId(currentLoginedUser.getId());
			List<ShareObject> list = shareObjectServices.selectSelective(usedBook, pagenation);
			tips = new Tips("查询成功！", true, true);
			tips.setData(list);
			tips.setPagenation(pagenation);
		}
		return tips;
	}

	@RequestMapping(value="/hotsearch")
	@ResponseBody
	public Tips hotsearch (ShareObject usedBook, Pagenation pagenation) {
		super.validLogined();
		if(isLogined) {
			List<ShareObject> list = shareObjectServices.hotsearch(usedBook, pagenation);
			tips = new Tips("查询成功！", true, true);
			tips.setData(list);
			tips.setPagenation(pagenation);
		}
		return tips;
	}

}
