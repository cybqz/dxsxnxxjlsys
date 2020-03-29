package com.cyb.blogserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cyb.blogserver.common.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.domain.UserRole;
import com.cyb.blogserver.vo.UserRolePermissionVO;
import com.cyb.blogserver.domain.Permission;
import com.cyb.blogserver.domain.RolePermission;
import com.cyb.blogserver.vo.RolePermissionVO;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.PermissionServices;
import com.cyb.blogserver.service.RolePermissionServices;
import com.cyb.blogserver.service.UserRoleServices;
import com.cyb.blogserver.service.UserServices;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@Autowired
	private UserServices userSerivces;
	@Autowired
	private UserRoleServices userRoleServices;
	@Autowired
	private PermissionServices permissionServices;
	@Autowired
	private RolePermissionServices rolePermissionServices;
	
	@RequestMapping(value="/update")
	@ResponseBody
	public Tips update (User param) {

		super.validLogined();
		if(isLogined) {
			if(StringUtils.isBlank(param.getName())) {
				tips.setMsg("用户名不能为空！");
			}else if(StringUtils.isBlank(param.getUserName())) {
				tips.setMsg("姓名不能为空！");
			}else if(StringUtils.isBlank(param.getEmail())) {
				tips.setMsg("邮箱地址不能为空！");
			}else if(StringUtils.isBlank(param.getPhone())) {
				tips.setMsg("联系方式不能为空！");
			}else {
				currentLoginedUser.setName(param.getName());
				currentLoginedUser.setEmail(param.getEmail());
				currentLoginedUser.setPhone(param.getPhone());
				currentLoginedUser.setUserName(param.getUserName());
				int count = userSerivces.updateByPrimaryKey(currentLoginedUser);
				if(count > 0) {
					tips = new Tips("修改成功！", true);
				}else {
					tips.setMsg("修改失败！");
				}
			}
		}
		return tips;
	}
	
	@RequestMapping(value="/updateIntroduce")
	@ResponseBody
	public Tips updateIntroduce (User param) {
		super.validLogined();
		if(isLogined) {
			if(StringUtils.isBlank(param.getIntroduce())) {
				tips.setMsg("用户简介不能为空！");
			}else {
				currentLoginedUser.setIntroduce(param.getIntroduce());
				int count = userSerivces.updateByPrimaryKey(currentLoginedUser);
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
	public UserRolePermissionVO getUser () {
		super.validLogined();
		if(isLogined) {
			UserRolePermissionVO userRolePermissionVO = UserRolePermissionVO.toUserRolePermissionVO(currentLoginedUser);
			List<UserRole> userRoles = userRoleServices.selectByUserId(currentLoginedUser.getId());
			if(userRoles != null && userRoles.size() > 0) {
				List<RolePermissionVO> rolePermissionVOs = new ArrayList<RolePermissionVO>();
				for(UserRole userRole : userRoles) {
					
					RolePermissionVO rolePermissionVO = RolePermissionVO.toRolePermissionVO(userRole);
					
					//查询当前角色的权限
					List<RolePermission> rolePermissions = rolePermissionServices.selectByRoleId(userRole.getRoleId());
					if(rolePermissions != null && rolePermissions.size() > 0) {
						List<Permission> permissions = new ArrayList<Permission>();
						for(RolePermission rolePermission : rolePermissions) {
							
							//查询权限
							Permission permission = permissionServices.selectByPrimaryKey(rolePermission.getPermissionId());
							permissions.add(permission);
						}
						rolePermissionVO.setPermissions(permissions);
					}
					rolePermissionVOs.add(rolePermissionVO);
				}
				userRolePermissionVO.setUserRoles(rolePermissionVOs);
			}
			return userRolePermissionVO;
		}
		return null;
	}


	/**
	 * 签到
	 * @return
	 */
	@RequestMapping(value="/signin")
	@ResponseBody
	public Tips signin () {
		return userSerivces.signin();
	}
}
