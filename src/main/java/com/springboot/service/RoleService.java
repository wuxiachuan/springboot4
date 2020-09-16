package com.springboot.service;




import com.springboot.domain.Menu;
import com.springboot.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRole();
    void insertRole(Role role);
    Role findById(int id);
    List<Menu> getmenus();
    List<String> allRoleNames();
}
