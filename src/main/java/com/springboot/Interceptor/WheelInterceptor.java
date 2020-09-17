package com.springboot.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class WheelInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    private SimpleDateFormat dateFormater;

    public WheelInterceptor(){
        this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getServletPath());
        String url = request.getServletPath();
        String token = request.getHeader("Authorization");
        String name = (String) redisTemplate.opsForValue().get(token);
        if (name == null) {
            return false;
        }
        String status = (String) redisTemplate.opsForHash().get(name+"token","status");
        if ("0".equals(status)){
            return false;
        }
        String ipaddr = request.getRemoteAddr();
        redisTemplate.opsForHash().putIfAbsent(name+"token","ip",ipaddr);
        redisTemplate.opsForList().leftPush(name+"log",url+"="+dateFormater.format(new Date())+"="+ipaddr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
