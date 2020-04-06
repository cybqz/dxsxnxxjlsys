package com.cyb.cleg.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/")
public class IndexController extends BaseController {

	@PostMapping("/")
	@ResponseBody
	public String image() {
		return "index.html";
	}
}
