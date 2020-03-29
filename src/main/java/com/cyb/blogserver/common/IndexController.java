package com.cyb.blogserver.common;

import com.cyb.blogserver.config.MyWebAppConfiguration;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.forum.common.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping(value="/")
public class IndexController extends BaseController {

	@PostMapping("/")
	@ResponseBody
	public String image() {
		return "index.html";
	}
}
