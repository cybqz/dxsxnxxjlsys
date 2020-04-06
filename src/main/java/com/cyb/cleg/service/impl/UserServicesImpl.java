package com.cyb.cleg.service.impl;

import java.util.Date;
import javax.annotation.Resource;

import com.cyb.authority.utils.EncryptionDecrypt;
import com.cyb.cleg.common.Constant;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.dao.SigninMapper;
import com.cyb.cleg.domain.Signin;
import com.cyb.cleg.service.AccumulatePointsServices;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.cleg.utils.UserValidate;
import org.springframework.stereotype.Service;
import com.cyb.cleg.dao.UserMapper;
import com.cyb.cleg.domain.User;
import com.cyb.cleg.service.UserServices;

@Service(value="userSerivces")
public class UserServicesImpl implements UserServices {
	
	@Resource
	private UserMapper userMapper;

	@Resource
	private SigninMapper signinMapper;

	@Resource
	private UserValidate userValidate;

	@Resource
	private AccumulatePointsServices accumulatePointsServices;


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
		userMapper.insert(record);
		return 1;
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
	public User selectByUserName(String username) {
		return userMapper.selectByUserName(username);
	}

	@Override
	public Tips signin(User user) {
		Tips tips = new Tips("false", false);
		Date now = new Date();
		Signin signinParam = new Signin(null, user.getId(), MyUtils.parse("yyyyMMDD", now));
		Signin signin = signinMapper.selectOneByUserDateTime(signinParam);
		if(null == signin){
			//签到
			signinParam.setDatetime(now);
			signinParam.setId(MyUtils.getPrimaryKey());
			signinMapper.insert(signinParam);

			//增加积分
			accumulatePointsServices.addPoints(user.getId(), Constant.PARAMES_NAME_SIGNIN);
			tips = new Tips("签到成功！", true);
		}else{
			tips = new Tips("今天已签到！", true);
		}
		return tips;
	}
}
