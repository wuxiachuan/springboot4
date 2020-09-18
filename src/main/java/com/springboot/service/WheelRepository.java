package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class WheelRepository {
    @Autowired
    private RedisTemplate redisTemplate;

    public List<Integer> putAxleInPosition(String axleNum){
        List<Integer> res = putInPosition(axleNum,"qualified");
        return res;
    }
    public List<Integer> discardAxleInPosition(String axleNum){
        List<Integer> res = putInPosition(axleNum,"discard");
        return res;
    }
    public List<Integer> putInPosition(String axleNum,String op){
        List<Integer> res = new ArrayList<>();
        List<Integer> xlist = redisTemplate.opsForList().range(op+"Repository",0,-1);
        int x=0;
        int y=0;
        for (Integer i : xlist){
            if (i != 0) break;
            x++;
        }
        if (x>9){
            return null;
        }
        List<String> ylist = redisTemplate.opsForList().range(op+x,0,-1);
        for (String axle : ylist){
            if ("null".equals(axle)) break;
            y++;
        }
        //存入轮对
        redisTemplate.opsForList().set(op+x,y,axleNum);
        //修改容量
        Integer count = (Integer) redisTemplate.opsForList().index(op+"Repository",x);
        redisTemplate.opsForList().set(op+"Repository",x,--count);
        //建立索引
        redisTemplate.opsForHash().put("position"+axleNum,"x",x);
        redisTemplate.opsForHash().put("position"+axleNum,"y",y);
        res.add(x);
        res.add(y);
        return res;
    }

    public void getAxleOutPosition(String axleNum){
        getOutPosition(axleNum,"qualified");
    }
    public void getDiscardAxleOutPosition(String axleNum){
        getOutPosition(axleNum,"discard");
    }
    public void getOutPosition(String axleNum,String op){
        if (redisTemplate.opsForHash().get("position"+axleNum,"x")==null) return;
        Integer x = (Integer) redisTemplate.opsForHash().get("position"+axleNum,"x");
        Integer y = (Integer) redisTemplate.opsForHash().get("position"+axleNum,"y");
        //取出轮对
        redisTemplate.opsForList().set(op+x,y,"null");
        //修改容量
        Integer count = (Integer) redisTemplate.opsForList().index(op+"Repository",x);
        redisTemplate.opsForList().set(op+"Repository",x,++count);
    }


}
