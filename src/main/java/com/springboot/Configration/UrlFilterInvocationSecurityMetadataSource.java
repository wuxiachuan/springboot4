package com.springboot.Configration;

import com.springboot.dao.UserDao;
import com.springboot.domain.Right;
import com.springboot.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private UserDao userDao;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println(requestUrl);
        int index = requestUrl.indexOf("/", 1);
        String url = requestUrl.substring(0,index);
        System.out.println(url);
        if ("/login".equals(requestUrl)) {
            return null;
        }
        List<Role> roleList = userDao.findAllRole();
        List<String> valuelist = new ArrayList<>();
        for (Role role : roleList){
            for (Right right : role.getRights()){
                if (right.getUrl().equals(url)){
                    valuelist.add(role.getRoleDesc());
                }
            }
        }
        System.out.println("valuelist:"+valuelist);
        if (valuelist!=null) {
            String[] values = new String[valuelist.size()];
            for (int i=0;i<valuelist.size();i++){
                values[i] = valuelist.get(i);
            }
            return SecurityConfig.createList(values);
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
