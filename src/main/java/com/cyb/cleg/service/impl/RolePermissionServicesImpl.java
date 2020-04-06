package com.cyb.cleg.service.impl;

import com.cyb.cleg.dao.RolePermissionMapper;
import com.cyb.cleg.domain.RolePermission;
import com.cyb.cleg.service.RolePermissionServices;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="rolePermissionServices")
public class RolePermissionServicesImpl implements RolePermissionServices {

	@Resource
	private RolePermissionMapper rolePermissionMapper;

	@Override
    public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RolePermission selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RolePermission> selectByRoleId(String roleId) {
		return null;
	}
}