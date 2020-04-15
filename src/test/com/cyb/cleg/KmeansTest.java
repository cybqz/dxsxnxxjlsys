package com.cyb.cleg;

import com.cyb.cleg.kmeans.Cluster;
import com.cyb.cleg.kmeans.KMeansRun;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.forum.dao.ForumMessageMapper;
import com.cyb.forum.domain.ForumMessage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KmeansTest {

    @Resource
    private ForumMessageMapper forumMessageMapper;

    @Test
    public void test(){

        List<ForumMessage> list = forumMessageMapper.selectSelective(null, 1, 500);
        if(null != list && list.size() > 0){

            ArrayList<float[]> dataSet = new ArrayList<float[]>();
            for(ForumMessage message : list){

                dataSet.add(new float[] { message.getContent().length(), message.getCreateDateTime().getTime()});
            }

            KMeansRun kRun =new KMeansRun(3, dataSet);

            Set<Cluster> clusterSet = kRun.run();
            System.out.println("单次迭代运行次数："+kRun.getIterTimes());
            for (Cluster cluster : clusterSet) {
                System.out.println(cluster);
            }
        }
    }


    private void initData(){
        try {

            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader("F:/Work/talk.txt"));

            String line = "";
            while ((line = reader.readLine()) != null){

                if(StringUtils.isNotEmpty(line)){
                    ForumMessage forumMessage = new ForumMessage();
                    forumMessage.setId(MyUtils.getPrimaryKey());
                    forumMessage.setUserId("ea23cde4-b653-4bd4-8817-5d34e8cc5930");
                    forumMessage.setContent(line);
                    forumMessage.setCreateDateTime(new Date(randomDate(random)));
                    forumMessage.setCollectCount(0);
                    forumMessage.setDiscussCount(0);
                    forumMessage.setForwardCount(0);
                    forumMessage.setPraiseCount(random.nextInt(1000));
                    forumMessageMapper.insert(forumMessage);
                }

            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Long randomDate(Random random) {

        long end = System.currentTimeMillis();
        long begin = end - (1000 * 60 * 60 * 24 * 60);

        long date = begin + (long)(Math.random()*(end - begin));
        while (date == begin || date == end){
            date = begin + (long)(Math.random()*(end - begin));
        }
        return date;
    }
}
