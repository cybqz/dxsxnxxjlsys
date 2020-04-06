package com.cyb.cleg.service.impl;

import com.cyb.cleg.common.Constant;
import com.cyb.cleg.dao.AccumulatePointsMapper;
import com.cyb.cleg.dao.ParamesMapper;
import com.cyb.cleg.dao.UserMapper;
import com.cyb.cleg.domain.AccumulatePoints;
import com.cyb.cleg.vo.AccumulatePointsVO;
import com.cyb.cleg.domain.Parames;
import com.cyb.cleg.domain.User;
import com.cyb.cleg.service.AccumulatePointsServices;
import com.cyb.cleg.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="accumulatePointsServices")
public class AccumulatePointsServicesImpl implements AccumulatePointsServices {

	private static Integer MAX_USER_ACCUMULATE_POINTS;
	
	@Resource
	private AccumulatePointsMapper accumulatePointsMapper;

	@Resource
	private ParamesMapper paramesMapper;

	@Resource
	private UserMapper userMapper;

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
		return accumulatePointsMapper.selectOneSelective(record, null, null);
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
			Date startDate = MyUtils.parse("yyyyMMDD", now);
			Date endDate = MyUtils.parse("yyyyMMDD", now);
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
			Parames parames = paramesMapper.selectOneByName(Constant.PARAMES_NAME_SIGNIN);
			accumulatePointsParam.setParameId(parames.getId());
			AccumulatePoints accumulatePoints = accumulatePointsMapper.selectOneSelective(accumulatePointsParam, startDate, endDate);
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

	@Override
	public List<AccumulatePointsVO> selectSystemTopTen() {

		List<Map<String,Object>> list = accumulatePointsMapper.selectTopList(10, null);
		List<AccumulatePointsVO> resultList = setUserName(list);
		return resultList;
	}

	@Override
	public List<AccumulatePointsVO> selectFriendsTopTen(String userId) {

		List<Map<String,Object>> list = accumulatePointsMapper.selectTopList(10, null);
		List<AccumulatePointsVO> resultList = setUserName(list);
		return resultList;
	}

	private List<AccumulatePointsVO> setUserName(List<Map<String,Object>> list){
		List<AccumulatePointsVO> resultList = null;
		if(null != list && !list.isEmpty()){
			resultList = new ArrayList<>(list.size());
			for(Map<String,Object> map : list){
				String userId = String.valueOf(map.get("userId"));
				AccumulatePointsVO vo = new AccumulatePointsVO();

				User user = userMapper.selectByPrimaryKey(userId);
				if(null != user){
					vo.setName(user.getUserName());
				}
				vo.setPoints(Integer.valueOf(String.valueOf(map.get("points"))));
				resultList.add(vo);
			}
		}
		return resultList;
	}
}
