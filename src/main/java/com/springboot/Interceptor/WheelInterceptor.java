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
        String terminal = request.getHeader("Terminal");
        //检测用户是否登录
        String name = (String) redisTemplate.opsForValue().get(token);
        if (name == null) {
            return false;
        }
        Boolean ismobile = false;
        if ("mobile".equals(terminal)){
            ismobile = true;
        }
        if (ismobile){
            String ipaddr = request.getRemoteAddr();
            redisTemplate.opsForHash().putIfAbsent(name+"mobtoken","ip",ipaddr);
            redisTemplate.opsForList().leftPush(name+"moblog",url+"="+dateFormater.format(new Date())+"="+ipaddr);
            return true;
        }else {
            //120分钟未发起请求自动下线
            redisTemplate.expire(token,Duration.ofMinutes(120));
            redisTemplate.expire(name+"token",Duration.ofMinutes(120));

            String ipaddr = request.getRemoteAddr();
            redisTemplate.opsForHash().putIfAbsent(name+"token","ip",ipaddr);
            redisTemplate.opsForList().leftPush(name+"log",url+"="+dateFormater.format(new Date())+"="+ipaddr);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
