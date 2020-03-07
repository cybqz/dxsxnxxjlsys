package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.ShareObject;
import com.cyb.blogserver.service.ShareObjectServices;
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
		Tips tips = new Tips("添加失败！", true, false);
		super.validLogined();
		if(null != user) {
			shareObject.setUserId(user.getId());
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
		Tips tips = new Tips("删除组队失败！", true, false);
		super.validLogined();
		if(null != user) {

		}
		return tips;
	}

	@RequestMapping(value="/page")
	@ResponseBody
	public Tips page (ShareObject usedBook, Pagenation pagenation) {
		Tips tips = new Tips("查询失败！", true, false);
		super.validLogined();
		if(null != user) {
			List<ShareObject> list = shareObjectServices.selectSelective(usedBook, pagenation);
			tips = new Tips("查询成功！", true, false);
			tips.setData(list);
			tips.setPagenation(pagenation);
		}
		return tips;
	}

}
