package com.springboot.service;

import com.springboot.dao.UserDao;
import com.springboot.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserInfo> users = userDao.findUserByName(s);
        if (users==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails user = userDao.findUserById(Integer.parseInt(users.get(0).getId()));
        return user;
    }
}
