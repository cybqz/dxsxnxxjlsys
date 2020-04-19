package com.cyb.cleg.service.impl;

import com.cyb.cleg.dao.ShareObjectMapper;
import com.cyb.cleg.domain.ShareObject;
import com.cyb.cleg.service.ShareObjectServices;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.common.pagination.Pagination;
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
    public int deleteByPrimaryKey(String id){
        return shareObjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ShareObject selectByPrimaryKey(String id) {
        return shareObjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShareObject> hotsearch(ShareObject record, Pagination pagination) {
        int count = shareObjectMapper.countByShareObject(record);
        pagination.setDataCount(count);
        List<ShareObject> list = shareObjectMapper.hotsearch(record, pagination);
        if(null != list && !list.isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<ShareObject> innerList = shareObjectMapper.selectSelective(record, null);
                    for(ShareObject shareObject : innerList){
                        shareObject.setHot(shareObject.getHot() + 1);
                        shareObjectMapper.updateHotByPrimaryKey(shareObject);
                    }
                }
            }).start();
        }
        return list;
    }

    @Override
    public List<ShareObject> selectSelective(ShareObject record, Pagination pagination) {
        int count = shareObjectMapper.countByShareObject(record);
        pagination.setDataCount(count);
        return shareObjectMapper.selectSelective(record, pagination);
    }
}
