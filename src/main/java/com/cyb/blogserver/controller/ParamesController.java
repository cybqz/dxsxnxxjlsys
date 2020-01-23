package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Parames;
import com.cyb.blogserver.service.ParamesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/parames")
public class ParamesController {

	@Autowired
	private ParamesServices paramesServices;

	/**
	 * 获取所有兴趣
	 * @return
	 */
	@RequestMapping(value="/getInterestAll")
	@ResponseBody
	public Tips getInterestAll () {
		Tips tips;
		Parames parames = new Parames();
		parames.setName(Constant.PARAMES_INTEREST);
		List<Parames> list = paramesServices.selectByParames(parames);
		tips = new Tips("true", true, list);
		return tips;
	}
}
