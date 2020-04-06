package com.cyb.cleg.controller;

import java.util.HashMap;
import java.util.Map;

import com.cyb.cleg.common.Tips;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/testController")
public class TestController {
	
	@RequestMapping(value="/test")
	@ResponseBody
	public Tips test () {
		Tips tips = new Tips("true", true);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "cyb");
		tips.setData(map);
		return tips;
	}
}
