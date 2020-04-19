package com.cyb.cleg.timer;

import com.cyb.authority.dao.UserMapper;
import com.cyb.authority.domain.User;
import com.cyb.cleg.dao.InterestMapper;
import com.cyb.cleg.dao.ParamesMapper;
import com.cyb.cleg.dao.ShareObjectMapper;
import com.cyb.cleg.domain.Interest;
import com.cyb.cleg.domain.Parames;
import com.cyb.cleg.domain.ShareObject;
import com.cyb.cleg.kmeans.Cluster;
import com.cyb.cleg.kmeans.KMeansRun;
import com.cyb.cleg.kmeans.Point;
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

    private static Integer CLUSTER_COUNT = 6;

    private static Integer RESULT_SIZE = 10;

    private static final String PATTERN = "yyy-MM-dd HH:mm:ss";

    public static Map<String, List<ForumMessage>> FORUM_MESSAGE_MAP = new HashMap<String, List<ForumMessage>>();

    public static Map<String, List<ShareObject>> SHARE_OBJECT_MAP = new HashMap<String, List<ShareObject>>();

    @Resource
    private UserMapper userMapper;

    @Resource
    private InterestMapper interestMapper;

    @Resource
    private ParamesMapper paramesMapper;

    @Resource
    private ForumMessageMapper forumMessageMapper;

    @Resource
    private ShareObjectMapper shareObjectMapper;

    @Scheduled(initialDelay = 10000, fixedDelay = 600000)
    public void forumMessageTask(){

        System.out.println("论坛智能推荐开始执行\t" + DateFormatUtils.format(new Date(), PATTERN));

        List<ForumMessage> forumMessageList = forumMessageMapper.selectSelective(new ForumMessage(), null);
        List<User> userList = userMapper.selectBySelective(new User());

        if(!CollectionUtils.isEmpty(userList) && !CollectionUtils.isEmpty(forumMessageList)){

            for(User user : userList){

                int weight = getWeight(user.getId());
                if(weight <= 0){
                    continue;
                }

                ArrayList<float[]> dataSet = new ArrayList<float[]>();
                for(ForumMessage message : forumMessageList){
                    dataSet.add(new float[] {
                            weight,
                            message.getContent().length(),
                            message.getCreateDateTime().getTime()});
                }

                KMeansRun kRun =new KMeansRun(CLUSTER_COUNT, dataSet);

                Set<Cluster> clusterSet = kRun.run();
                System.out.println("单次迭代运行次数："+kRun.getIterTimes());
                for (Cluster cluster : clusterSet) {
                    List<ForumMessage> resultList = new ArrayList<>(RESULT_SIZE);
                    List<Point> points = cluster.getMembers();
                    int size = points.size();
                    List<Point> list = points.subList(0,size>RESULT_SIZE?RESULT_SIZE:size);
                    for(Point point : list){
                        resultList.add(forumMessageList.get(point.getId()));
                    }
                    FORUM_MESSAGE_MAP.put(user.getId(), resultList);
                }
            }
        }
    }

    @Scheduled(initialDelay = 15000, fixedDelay = 600000)
    public void shareObjectTask(){

        System.out.println("二手共享智能推荐开始执行\t" + DateFormatUtils.format(new Date(), PATTERN));

        List<ShareObject> shareObjectList = shareObjectMapper.selectSelective(new ShareObject(), null);
        List<User> userList = userMapper.selectBySelective(new User());

        if(!CollectionUtils.isEmpty(userList) && !CollectionUtils.isEmpty(shareObjectList)){

            for(User user : userList){

                int weight = getWeight(user.getId());
                if(weight <= 0){
                    continue;
                }

                ArrayList<float[]> dataSet = new ArrayList<float[]>();
                for(ShareObject shareObject : shareObjectList){
                    dataSet.add(new float[] {
                            weight,
                            shareObject.getTitle().length(),
                            shareObject.getCreateDateTime().getTime()});
                }

                KMeansRun kRun =new KMeansRun(CLUSTER_COUNT, dataSet);

                Set<Cluster> clusterSet = kRun.run();
                System.out.println("单次迭代运行次数："+kRun.getIterTimes());
                for (Cluster cluster : clusterSet) {

                    List<ShareObject> resultList = new ArrayList<>(RESULT_SIZE);
                    List<Point> points = cluster.getMembers();
                    int size = points.size();
                    List<Point> list = points.subList(0,size>RESULT_SIZE?RESULT_SIZE:size);
                    for(Point point : list){
                        resultList.add(shareObjectList.get(point.getId()));
                    }
                    SHARE_OBJECT_MAP.put(user.getId(), resultList);
                }
            }
        }
    }

    private int getWeight(String userId){
        int weight = 0;
        Interest interestParam = new Interest();
        interestParam.setUserId(userId);
        List<Interest> interestList = interestMapper.selectSelective(interestParam);
        if(!CollectionUtils.isEmpty(interestList)){
            for(Interest interest : interestList){
                Parames parames = paramesMapper.selectByPrimaryKey(interest.getParameId());
                weight += parames.getWeight();
            }
        }
        return weight;
    }
}
