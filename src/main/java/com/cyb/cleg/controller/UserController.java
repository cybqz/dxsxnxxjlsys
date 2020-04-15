package com.cyb.cleg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cyb.authority.base.BaseController;
import com.cyb.authority.domain.*;
import com.cyb.authority.service.*;
import com.cyb.cleg.common.Constant;
import com.cyb.cleg.vo.UserRoleVO;
import com.cyb.common.tips.Tips;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cyb.cleg.vo.UserRolePermissionVO;
import com.cyb.cleg.vo.RolePermissionVO;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userSerivce;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@RequestMapping(value="/update")
	@ResponseBody
	public Tips updateIntroduce (User param) {
		super.validLogined();
		if(isLogined) {
			if(StringUtils.isBlank(param.getIntroduce())) {
				tips.setMsg("用户简介不能为空！");
			}else if(StringUtils.isBlank(param.getUserName())){
				tips.setMsg("用户名称不能为空！");
			}else {
				currentLoginedUser.setIntroduce(param.getIntroduce());
				int count = userSerivce.updateByPrimaryKey(currentLoginedUser);
				if(count > 0) {
					tips = new Tips("修改成功！", true);
				}else {
					tips.setMsg("修改失败！");
				}
			}
		}
		return tips;
	}
	
	@RequestMapping(value="/updateImage")
	@ResponseBody
	public Tips updateImage (@RequestParam(value = "file", required = true) MultipartFile pictureFile) {
		super.validLogined();
		if(isLogined) {
            try {
            	if(pictureFile != null) {
            		// 图片新名字
                    String newName = UUID.randomUUID().toString();
                    // 图片原来的名字
                    String oldName = pictureFile.getOriginalFilename();
                    // 后缀
                    String sux = oldName.substring(oldName.lastIndexOf("."));
                     //新建本地文件流
                    //File file = new File("D:\\SSMBasic\\src\\main\\webapp\\WEB-INF\\img\\" + newName + sux);
                    // 写入本地磁盘
    				//pictureFile.transferTo(file);
    				tips = new Tips("true", true);
            	}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		return tips;
	}
	
	@RequestMapping(value="/getUser")
	@ResponseBody
	public UserRoleVO getUser () {
		super.validLogined();
		if(isLogined) {
			UserRoleVO userRoleVO = UserRoleVO.toUserRolePermissionVO(currentLoginedUser);
			List<UserRole> userRoles = userRoleService.selectByUserId(currentLoginedUser.getId());
			if(userRoles != null && userRoles.size() > 0) {
				for(UserRole userRole : userRoles) {
					Role role = roleService.selectByPrimaryKey(userRole.getRoleId());
					if(null != role && role.getName().equals(Constant.ROLE_ADMIN)){
						userRoleVO.setHasRoleAdmin(true);
						return userRoleVO;
					}
				}
			}
			return userRoleVO;
		}
		return null;
	}
}
