package com.cyb.cleg.vo;

import com.cyb.authority.domain.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class UserRoleVO extends User {

	public boolean hasRoleAdmin = false;


	public boolean isHasRoleAdmin() {
		return hasRoleAdmin;
	}

	public void setHasRoleAdmin(boolean hasRoleAdmin) {
		this.hasRoleAdmin = hasRoleAdmin;
	}
	
	public static UserRoleVO toUserRolePermissionVO(User user) {
		UserRoleVO vo = new UserRoleVO();
		BeanUtils.copyProperties(user, vo);
		return vo;
	}
}