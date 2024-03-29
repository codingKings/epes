package com.epes.demo.service;

import com.epes.demo.dao.RoleDao;
import com.epes.demo.entity.Role;
import com.epes.demo.tool.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:01:36
* @ClassName 类名称：
* @Description 类描述：
*/

@Service
public class RoleService {

    private final BaseService baseService;
    private final RoleDao roleDao;


    @Autowired
    public RoleService(BaseService baseService, RoleDao roleDao) {
        this.baseService = baseService;
        this.roleDao = roleDao;
    }

    @Transactional
    public Map<String, String> add(List<Role> roleList){
        for (Role role : roleList) {
            role.setId(UUID.randomUUID().toString());
            baseService.insert(role);
        }
        return null;
    }

    public Map<String, String> update(Role role){
        baseService.update(role);
        return null;
    }

    public Map<String, String> delete(String id){
        baseService.delete(Role.class,id);
        return null;
    }

    public List<Map<String, Object>> pageFind(PageRequest pageRequest, SearchParams searchParams){
        return baseService.pageFindByCondition(Role.class, pageRequest, searchParams);
    }

    public Role findRole(String id){
        return roleDao.findRole(id);
    }
}
