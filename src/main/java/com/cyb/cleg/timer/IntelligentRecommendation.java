package com.cyb.cleg.timer;

import com.cyb.authority.dao.UserMapper;
import com.cyb.authority.domain.User;
import com.cyb.cleg.kmeans.Cluster;
import com.cyb.cleg.kmeans.KMeansRun;
import com.cyb.forum.dao.ForumMessageMapper;
import com.cyb.forum.domain.ForumMessage;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 智能推荐定时任务
 */
@Component
@EnableScheduling
public class IntelligentRecommendation {

    private static final String PATTERN = "yyy-MM-dd HH:mm:ss";

    @Resource
    private UserMapper userMapper;

    @Resource
    private ForumMessageMapper forumMessageMapper;

    @Scheduled(initialDelay = 10000, fixedDelay = 60000)
    public void forumMessageTask(){

        System.out.println("论坛智能推荐开始执行\t" + DateFormatUtils.format(new Date(), PATTERN));

        List<ForumMessage> forumMessageList = forumMessageMapper.selectSelective(new ForumMessage(), 1, 500);
        List<User> userList = userMapper.selectBySelective(new User());

        if(!CollectionUtils.isEmpty(userList) && !CollectionUtils.isEmpty(forumMessageList)){

            for(User user : userList){

                ArrayList<float[]> dataSet = new ArrayList<float[]>();
                for(ForumMessage message : forumMessageList){
                    dataSet.add(new float[] {
                            user.getUserName().length(),
                            message.getContent().length(),
                            message.getCreateDateTime().getTime()});
                }

                KMeansRun kRun =new KMeansRun(3, dataSet);

                Set<Cluster> clusterSet = kRun.run();
                System.out.println("单次迭代运行次数："+kRun.getIterTimes());
                for (Cluster cluster : clusterSet) {
                    System.out.println(cluster);
                }
            }
        }
    }
}
