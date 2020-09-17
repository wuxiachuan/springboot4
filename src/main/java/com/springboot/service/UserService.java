package com.springboot.service;

import com.springboot.domain.Right;
import com.springboot.domain.Role;
import com.springboot.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> login(String name, String password);
    UserInfo findUserById(Integer id);
    List<UserInfo> findUserByName (String name);
    List<UserInfo> findAllUser(String online);
    List<UserInfo> findAllUserLog(String online);
    void updateUserStatu(UserInfo user);
    void updateUser(UserInfo user, List<String> roles);
    void addUser(UserInfo user, List<String> roles);
    void deleteUser(Integer id);

    Role findRoleById(Integer id);
    List<Role> findAllRole();
    List<Role> findAllModRole();

    List<Right> findAllRight();
    List<Right> findAllRightlist();
    List<Right> findRoleRight(String roleid);
    void deleteRight(String roleid,String rightid,String subrightid);
    void assignmentRight(String rid,List<String> subrightid);


    void insertRRmap(String rid,String id,String sid);
    void createRightTable();

}
