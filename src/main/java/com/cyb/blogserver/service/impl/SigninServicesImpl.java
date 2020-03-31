package com.cyb.blogserver.service.impl;

import com.cyb.authority.utils.EncryptionDecrypt;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.dao.SigninMapper;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.Signin;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.AccumulatePointsServices;
import com.cyb.blogserver.service.SigninServices;
import com.cyb.blogserver.service.UserServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value="signinServices")
public class SigninServicesImpl implements SigninServices {

	@Resource
	private SigninMapper signinMapper;

	@Resource
	private AccumulatePointsServices accumulatePointsServices;

	@Override
	public int deleteByPrimaryKey(String id) {
		return signinMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Signin record) {
		return signinMapper.insert(record);
	}

	@Override
	public int insertSelective(Signin record) {
		return signinMapper.insertSelective(record);
	}

	@Override
	public Signin selectByPrimaryKey(String id) {
		return signinMapper.selectByPrimaryKey(id);
	}

	@Override
	public Signin selectOneByUserDateTime(Signin record) {
		return signinMapper.selectOneByUserDateTime(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Signin record) {
		return signinMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Signin record) {
		return signinMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Tips signin(User user) {
		Tips tips = new Tips("false", false);
		Date now = new Date();
		if(isSiginToday(user)){
			//签到
			Signin signinParam = new Signin(null, user.getId(), MyUtils.parse("yyyyMMDD", now));
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

	@Override
	public boolean isSiginToday(User user) {
		Date now = new Date();
		Signin signinParam = new Signin(null, user.getId(), MyUtils.parse("yyyyMMDD", now));
		Signin signin = signinMapper.selectOneByUserDateTime(signinParam);
		return null == signin;
	}
}
