package com.springboot;

import com.mysql.cj.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.time.Duration.*;

@SpringBootTest
class Springboot4ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate redisTemplate;
    Random random = new Random(47);
    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }
    @Test
    public  void testredis(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list);
    }

    @Test
    public  void testredisTemplate(){
        redisTemplate.opsForHash().put("team2","name","java");
        redisTemplate.opsForValue().set("token","value",Duration.ofMinutes(10L));
       String name = (String) redisTemplate.opsForHash().get("team2","name");
        System.out.println(name);
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

}


