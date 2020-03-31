package com.cyb.blogserver.service.impl;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.authority.utils.EncryptionDecrypt;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.dao.InterestMapper;
import com.cyb.blogserver.dao.ParamesMapper;
import com.cyb.blogserver.dao.SigninMapper;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.Parames;
import com.cyb.blogserver.domain.Signin;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.InterestServices;
import com.cyb.blogserver.service.UserServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.utils.UserValidate;
import com.cyb.forum.common.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service(value="interestServices")
public class InterestServicesImpl implements InterestServices {

	private static int MAX_USER_INTEREST_COUNT = 5;
	
	@Resource
	private InterestMapper interestMapper;

	@Resource
	private ParamesMapper paramesMapper;

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

	/**
	 * 编辑用户兴趣
	 * @param id 用户ID
	 * @param interestList 兴趣ID
	 * @return
	 */
	@Override
	public R editUserInterest(String id, List<String> interestList) {

		if(null != interestList && interestList.size() > 0 && interestList.size() <= MAX_USER_INTEREST_COUNT){

			R validateInterest = validateInterest(interestList);
			if(validateInterest.isSuccess()){

				//删除旧的用户兴趣
				Interest interestParamme = new Interest(null, id, null, null);
				List<Interest> list = interestMapper.selectSelective(interestParamme);
				if(null != list || list.size() > 0){
					for(Interest interest : list){
						interestMapper.deleteByPrimaryKey(interest.getId());
					}
				}

				for(int i = 0; i < interestList.size(); i++){
					Interest interest = new Interest(MyUtils.getPrimaryKey(), id, interestList.get(i), new Date());
					interestMapper.insert(interest);
				}

				return R.success("兴趣编辑成功");
			}else{
				return validateInterest;
			}
		}
		return R.fail("兴趣选择错误");
	}

	@Override
	public R validateInterest(List<String> list){
		if(list != null && !list.isEmpty()){
			for(String parameId : list){
				Parames parames = paramesMapper.selectByPrimaryKey(parameId);
				if(parames == null){
					return R.fail("选择的兴趣不存在");
				}
			}
			return R.success("success");
		}
		return R.fail("未选择兴趣标签");
	}
}
