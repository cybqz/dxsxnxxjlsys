package com.cyb.cleg.service.impl;

import javax.annotation.Resource;

import com.cyb.cleg.domain.Parames;
import org.springframework.stereotype.Service;
import com.cyb.cleg.dao.ParamesMapper;
import com.cyb.cleg.service.ParamesServices;

import java.util.List;

@Service(value="paramesServices")
public class ParamesServicesImpl implements ParamesServices {
	
	@Resource
	private ParamesMapper paramesMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return paramesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Parames record) {
		return paramesMapper.insert(record);
	}

	@Override
	public int insertSelective(Parames record) {
		return paramesMapper.insertSelective(record);
	}

	@Override
	public Parames selectByPrimaryKey(String id) {
		return paramesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Parames record) {
		return paramesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Parames record) {
		return paramesMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Parames> selectByParames(Parames record) {
		return paramesMapper.selectByParames(record);
	}
}
