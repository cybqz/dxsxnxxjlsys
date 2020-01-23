package com.cyb.blogserver.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.authority.utils.EncryptionDecrypt;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.dao.SigninMapper;
import com.cyb.blogserver.domain.Signin;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.utils.UserValidate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.UserServices;

@Service(value="userSerivces")
public class UserServicesImpl implements UserServices {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private SigninMapper signinMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record, String basePath) {
		String password = EncryptionDecrypt.encryptionMD5(record.getPassword());
		record.setPassword(password);
		Integer sex = record.getSex();
		String image = "/headportrait/";
		if(sex == 0) {
			image += "gril.png";
		}else {
			image += "boy.png";
		}
		record.setImage(image);
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
    public Set<String> queryRolesByName(String userName) {
		return userMapper.queryRolesByName(userName);
	}
	
	@Override
	public Set<String> queryPermissionByName(String userName) {
		return userMapper.queryPermissionByName(userName);
	}

	@Override
	public User selectByUserName(String username) {
		return userMapper.selectByUserName(username);
	}

	@Override
	public Tips signin() {
		Tips tips = new Tips("false", false);
		UserValidate validate = new UserValidate();
		User user = validate.isLoginNoAuthenticated();
		if(user == null) {
			Date now = new Date();
			Signin signinParam = new Signin(null, user.getId(), MyUtils.parse("yyyyMMDD", now));
			Signin signin = signinMapper.selectOneByUserDateTime(signinParam);
			if(null == signin){
				//签到
				signinParam.setDatetime(now);
				signinParam.setId(MyUtils.getPrimaryKey());
				signinMapper.insert(signinParam);
				tips = new Tips("签到成功！", true);
			}else{
				tips = new Tips("今天已签到！", true);
			}
		}
		return tips;
	}
}
