package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.AccumulatePointsMapper;
import com.cyb.blogserver.domain.AccumulatePoints;
import com.cyb.blogserver.service.AccumulatePointsServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value="accumulatePointsServices")
public class AccumulatePointsServicesImpl implements AccumulatePointsServices {
	
	@Resource
	private AccumulatePointsMapper accumulatePointsMapper;

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
	public int updateByPrimaryKeySelective(AccumulatePoints record) {
		return accumulatePointsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccumulatePoints record) {
		return accumulatePointsMapper.updateByPrimaryKey(record);
	}
}
