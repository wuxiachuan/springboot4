package com.springboot.service;

import com.github.pagehelper.PageHelper;
import com.springboot.dao.UserDao;
import com.springboot.domain.Right;
import com.springboot.domain.Role;
import com.springboot.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CacheConfig(cacheNames = "rights")
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;
    private Map<String,String> rightMap;

    @Override
    public UserInfo login(String name, String password) {
      UserInfo user =  userDao.findUserByNameAndPassword(name,password);
        return user;
    }

    @Override
    public UserInfo findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<UserInfo> findUserByName(String name) {
        return userDao.findUserByName("%"+name+"%");
    }

    @Override
    public List<UserInfo> findAllUser(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return userDao.findAllUser();
    }

    @Override
    public void updateUserStatu(UserInfo user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUser(UserInfo user, List<String> roles) {
        userDao.updateUser(user);
        userDao.deleteURmap(Integer.parseInt(user.getId()));
        for (String roleId : roles){
            userDao.insertURmap(Integer.parseInt(user.getId()),Integer.parseInt(roleId));
        }
    }

    @Override
    public void addUser(UserInfo user, List<String> roles) {
        userDao.addUser(user);
        for (String roleId : roles){
            userDao.insertURmap(Integer.parseInt(user.getId()),Integer.parseInt(roleId));
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
        userDao.deleteURmap(id);
    }

    @Override
    public Role findRoleById(Integer id) {
        return userDao.findRoleById(id);
    }

    @Override
   // @Cacheable(key = "#orgrole")
    public List<Role> findAllRole() {
          List<Role> rolelist = userDao.findAllRole();
          return rolelist;
    }

    @Override
    //@Cacheable(key = "#modrole")
    public List<Role> findAllModRole() {
        List<Role> roles = userDao.findAllModRole();
        for (Role role : roles){
            List<Right> rights = userDao.findRightByRoleId(Integer.parseInt(role.getId()));
            role.setRights(rights);
            for (Right right : rights){
                List<Right> subrights = userDao.findSubRightByRightId(Integer.parseInt(role.getId()),Integer.parseInt(right.getId()));
                right.setSubRight(subrights);
            }
        }
        return roles;
    }


    @Override
   // @Cacheable(key = "#rightstree")
    public List<Right> findAllRight() {
        return userDao.findAllRight();
    }
    //@Cacheable(key = "#rightslist")
    public List<Right> findAllRightlist() {
        List<Right> list = findAllRight();
        List<Right> res = new ArrayList<>();
        for(Right right : list){
            res.add(right);
            for(Right subright : right.getSubRight()){
                res.add(subright);
            }
        }
        return res;
    }

    @Override
    public List<Right> findRoleRight(String roleid) {
        List<Right> rights = userDao.findRightByRoleId(Integer.parseInt(roleid));
        List<Right> subrights=null;
        for (Right right : rights){
            int id = Integer.parseInt(right.getId());
            subrights = userDao.findSubRightByRightId(Integer.parseInt(roleid),id);
            right.setSubRight(subrights);
        }
        return rights;
    }

    @Override
    //@CacheEvict(key = "#modrole")
    public void deleteRight(String roleid, String rightid, String subrightid) {
        if (subrightid==null){
            userDao.deleteRight(Integer.parseInt(roleid),Integer.parseInt(rightid));
            return;
        }
       userDao.deleteRight2(Integer.parseInt(roleid),Integer.parseInt(rightid),Integer.parseInt(subrightid));
    }

    @Override
    public void assignmentRight(String rid, List<String> subrightid) {
        List<Integer> subrights = userDao.findAllSubrightid();
        if (rightMap == null){
            rightMap = new HashMap<>();
            for (Integer subid : subrights){
                Integer rightid = userDao.findRightbuysubRight(subid);
                rightMap.put(subid.toString(),rightid.toString());
            }
        }
        userDao.deleteRoleAllRight(Integer.parseInt(rid));
        for (String subid : subrightid){
            String rightid = rightMap.get(subid);
            if (rightid != null){
                insertRRmap(rid,rightid,subid);
            }
        }
    }

    @Override
    public void insertRRmap(String id,String rid,String sid) {
          userDao.insertRRmap(Integer.parseInt(rid),Integer.parseInt(id),Integer.parseInt(sid));
    }
    public void createRightTable(){
        List<Role> roles = findAllRole();
        List<Right> rights;
        for(Role role : roles){
            String id =role.getId();
            for(Right right: role.getRights()){
                String rid = right.getId();
                for (Right subright : right.getSubRight()){
                    String sid = subright.getId();
                    insertRRmap(id,rid,sid);
                }
            }
        }
    }
}
