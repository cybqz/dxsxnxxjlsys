package com.cyb.blogserver.service.impl;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.authority.utils.EncryptionDecrypt;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.dao.InterestMapper;
import com.cyb.blogserver.dao.SigninMapper;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.Signin;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.InterestServices;
import com.cyb.blogserver.service.UserServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.utils.UserValidate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service(value="interestServices")
public class InterestServicesImpl implements InterestServices {
	
	@Resource
	private InterestMapper interestMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return interestMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Interest record) {
		return interestMapper.insert(record);
	}

	@Override
	public int insertSelective(Interest record) {
		return interestMapper.insertSelective(record);
	}

	@Override
	public Interest selectByPrimaryKey(String id) {
		return interestMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Interest record) {
		return interestMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Interest record) {
		return interestMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Interest> selectSelective(Interest record) {
		return interestMapper.selectSelective(record);
	}
}
