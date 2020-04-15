package com.cyb.cleg;

import com.cyb.cleg.utils.MyUtils;
import com.cyb.forum.domain.ForumMessage;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        try {

            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader("F:/Work/talk.txt"));

            String line = "";
            while ((line = reader.readLine()) != null){

                if(StringUtils.isNotEmpty(line)){
                    System.out.println(line);
                }

            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
