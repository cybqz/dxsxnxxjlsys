package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.dao.ShareObjectMapper;
import com.cyb.blogserver.domain.ShareObject;
import com.cyb.blogserver.service.ShareObjectServices;
import com.cyb.blogserver.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value="shareObjectServices")
public class ShareObjectServicesImpl implements ShareObjectServices {

    @Resource
    private ShareObjectMapper shareObjectMapper;

    @Override
    public int insert(ShareObject record) {

        record.setId(MyUtils.getPrimaryKey());
        record.setCreateDateTime(new Date());
        record.setShared(0);
        return shareObjectMapper.insert(record);
    }

    @Override
    public int insertSelective(ShareObject record) {
        return shareObjectMapper.insertSelective(record);
    }

    @Override
    public ShareObject selectByPrimaryKey(String id) {
        return shareObjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShareObject> selectSelective(ShareObject record, Pagenation pagenation) {
        return shareObjectMapper.selectSelective(record, pagenation);
    }
}
