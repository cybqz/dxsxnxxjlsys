package com.cyb.cleg.controller;

import com.cyb.cleg.common.Constant;
import com.cyb.cleg.domain.Parames;
import com.cyb.cleg.service.ParamesServices;
import com.cyb.common.tips.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
		Parames parames = new Parames(null, Constant.PARAMES_INTEREST, null, null);
		List<Parames> list = paramesServices.selectByParames(parames);
		tips = new Tips("true", true, list);
		return tips;
	}
}
