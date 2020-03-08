package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.dao.AccumulatePointsMapper;
import com.cyb.blogserver.dao.ParamesMapper;
import com.cyb.blogserver.domain.AccumulatePoints;
import com.cyb.blogserver.domain.Parames;
import com.cyb.blogserver.service.AccumulatePointsServices;
import com.cyb.blogserver.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value="accumulatePointsServices")
public class AccumulatePointsServicesImpl implements AccumulatePointsServices {

	private static Integer MAX_USER_ACCUMULATE_POINTS;
	
	@Resource
	private AccumulatePointsMapper accumulatePointsMapper;

	@Resource
	private ParamesMapper paramesMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return accumulatePointsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccumulatePoints record) {
		return accumulatePointsMapper.insert(record);
	}

	@Override
	public int insertSelective(AccumulatePoints record) {
		return accumulatePointsMapper.insertSelective(record);
	}

	@Override
	public AccumulatePoints selectByPrimaryKey(String id) {
		return accumulatePointsMapper.selectByPrimaryKey(id);
	}

	@Override
	public AccumulatePoints selectOneSelective(AccumulatePoints record) {
		return accumulatePointsMapper.selectOneSelective(record);
	}

	/**
	 * 增加积分
	 * @param userId
	 * @param type
	 * @return
	 */
	@Override
	public int addPoints(String userId, String type) {
		AccumulatePoints accumulatePointsParam = new AccumulatePoints(null, userId, null, null, null);

		if(type.equals(Constant.PARAMES_NAME_SIGNIN)){
			Date now = new Date();
			accumulatePointsParam.setUpdateDate(MyUtils.parse("yyyyMMDD", now));
			AccumulatePoints accumulatePoints = accumulatePointsMapper.selectOneSelective(accumulatePointsParam);
			if(null == accumulatePoints){
				Parames paramesParam = new Parames(null, Constant.PARAMES_NAME_SIGNIN, null, Constant.PARAMES_GROUP_POINTS);
				List<Parames> paramesList = paramesMapper.selectByParames(paramesParam);
				if(null != paramesList && paramesList.size() > 0){

					String parameId = paramesList.get(0).getId();
					accumulatePoints = new AccumulatePoints(MyUtils.getPrimaryKey(), userId, parameId, 10, new Date());
					accumulatePointsMapper.insert(accumulatePoints);
				}
			}
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AccumulatePoints record) {
		return accumulatePointsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccumulatePoints record) {
		return accumulatePointsMapper.updateByPrimaryKey(record);
	}
}
