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
		super.validLogined();
		if(isLogined) {
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
