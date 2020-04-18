package com.cyb.cleg;

import com.cyb.cleg.dao.ShareObjectMapper;
import com.cyb.cleg.domain.ShareObject;
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

    @Resource
    private ShareObjectMapper shareObjectMapper;

    @Test
    public void test(){

        //initShareObjectData();
    }

    private void testForum(){

        List<ForumMessage> list = forumMessageMapper.selectSelective(null, null);
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

    private void testShareObject(){

        List<ShareObject> list = shareObjectMapper.selectSelective(new ShareObject(), null);
        if(null != list && list.size() > 0){

            ArrayList<float[]> dataSet = new ArrayList<float[]>();
            for(ShareObject shareObject : list){

                dataSet.add(new float[] { shareObject.getTitle().length(), shareObject.getCreateDateTime().getTime()});
            }

            KMeansRun kRun =new KMeansRun(3, dataSet);

            Set<Cluster> clusterSet = kRun.run();
            System.out.println("单次迭代运行次数："+kRun.getIterTimes());
            for (Cluster cluster : clusterSet) {
                System.out.println(cluster);
            }
        }
    }

    private void initForumData(){
        try {

            String path = System.getProperty("user.dir");

            System.out.println(path);
            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader(path + "\\data\\forum.txt"));

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

    private void initShareObjectData(){
        try {

            String path = System.getProperty("user.dir");

            System.out.println(path);
            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader(path + "\\data\\book.txt"));

            String line = "";
            while ((line = reader.readLine()) != null){

                if(StringUtils.isNotEmpty(line)){
                    ShareObject shareObject = new ShareObject();
                    shareObject.setId(MyUtils.getPrimaryKey());
                    shareObject.setUserId("ea23cde4-b653-4bd4-8817-5d34e8cc5930");
                    shareObject.setTitle(line);
                    shareObject.setShared(0);
                    shareObject.setFlag(String.valueOf(random.nextInt(4)));
                    shareObject.setCreateDateTime(new Date(randomDate(random)));
                    shareObject.setHot(random.nextInt(1000));
                    shareObject.setAuthorName("甲三");
                    shareObject.setDiscribe("这是一本好书");
                    shareObject.setFlag(String.valueOf(random.nextInt(100)));
                    shareObject.setPrice(String.valueOf(random.nextInt(100)));

                    shareObjectMapper.insert(shareObject);
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
