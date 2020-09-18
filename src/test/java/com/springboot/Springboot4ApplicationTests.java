package com.springboot;

import com.mysql.cj.util.TimeUtil;
import com.springboot.service.WheelRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.time.Duration.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot4ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    WheelRepository wheelRepository;
    Random random = new Random(47);
    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }
    @Test
    public  void createQualifiedWheelRepository(){
        for (int i=0;i<10;i++){
            redisTemplate.opsForList().rightPush("qualifiedRepository",100);
        }
        for (int i=0;i<10;i++){
            String name = "qualified"+i;
            for (int j=0;j<100;j++){
                redisTemplate.opsForList().rightPush(name,"null");
            }
        }
    }
    @Test
    public  void createDiscardWheelRepository(){
        for (int i=0;i<3;i++){
            redisTemplate.opsForList().rightPush("discardRepository",100);
        }
        for (int i=0;i<3;i++){
            String name = "discard"+i;
            for (int j=0;j<100;j++){
                redisTemplate.opsForList().rightPush(name,"null");
            }
        }
    }
    @Test
    public void testStoreWheel(){
        List<Integer> position = wheelRepository.putAxleInPosition("003");
        System.out.println(position.get(0));
        System.out.println(position.get(1));
    }

    @Test
    public void dateFormate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-M-d");
        String now = dateFormater.format(new Date());
        String[] date = now.split("-");
        System.out.println(date[0]);
        System.out.println(date[1]);
        System.out.println(date[2]);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int ty = 0;
        int tm = 0;
        tm = (year-1990)*12+month-22*12+5;
        ty = 1990+tm/12;
        tm = tm%12;
        String res = "";
        if (tm<10) {
            res = ty+"-0"+tm +"-00";
        }else {
            res = ty+"-"+tm +"-00";
        }
        System.out.println(res);
    }

    public String generateAxledate(){
        String axlemadeDate = null;
        int y = 1998;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(22);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }
    public String generatewheelDiam(){
        String axlemadeDate = null;
        int y = 780;
        y = y + random.nextInt(40);
        axlemadeDate = y + "";
        return axlemadeDate;
    }
    @Test
    public void testgendate(){
        for (int i = 0;i<20;i++){
           // System.out.println(generateAxledate());
            System.out.println(generatewheelDiam());
        }
    }

    @Test
    public void split(){
        String str = "/userManage/getusersLog=2020-09-17 14:02:13=0:0:0:0:0:0:0:1";
        String[] arr = str.split("=");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

    }

}


