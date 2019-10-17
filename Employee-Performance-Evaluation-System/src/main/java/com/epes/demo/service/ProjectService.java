package com.epes.demo.service;

import com.epes.demo.dao.ProjectDao;
import com.epes.demo.dao.ProjectTypeDao;
import com.epes.demo.entity.Department;
import com.epes.demo.entity.Project;
import com.epes.demo.entity.ProjectType;
import com.epes.demo.tool.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:01:36
* @ClassName 类名称：
* @Description 类描述：
*/

@Transactional
@Service
public class ProjectService {
    private final BaseService baseService;
    private final ProjectDao projectDao;
    private final ProjectTypeDao projectTypeDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectService(BaseService baseService, ProjectDao projectDao, ProjectTypeDao projectTypeDao, JdbcTemplate jdbcTemplate) {
        this.baseService = baseService;
        this.projectDao = projectDao;
        this.projectTypeDao = projectTypeDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Map<String, String> addPorject(Project project){
        Map<String, String> result = new HashMap<>(0);
        project.setId(UUID.randomUUID().toString());
        int i = baseService.insert(project);
        if (i>0){
            result.put("success","success");
            result.put("msg","新增成功");
        }else {
            result.put("success","error");
            result.put("msg","新增失败,无法连接数据库");
        }
        return result;
    }

    @Transactional
    public Map<String, String> delete(String id ){
        Map<String, String> result = new HashMap<>(0);
        int i = baseService.delete(Project.class, id);
        if (i>0){
            result.put("success","success");
            result.put("msg","删除成功");
        }else {
            result.put("success","error");
            result.put("msg","删除失败,无法连接数据库");
        }
        return result;
    }

    @Transactional
    public Map<String, String> update(Project project){
        int p = baseService.update(project);
        Map<String,String> map = new HashMap<>(0);
        if (p>0){
            map.put("msg","修改成功");
            map.put("success","success");
        }else {
            map.put("msg","数据不存在或数据无法修改");
            map.put("success","error");
        }
        return map;
    }

    public List<Map<String, Object>> findAllByPage(PageRequest pageRequest, SearchParams searchParams){
        return baseService.pageFindByCondition(Project.class, pageRequest, searchParams);
    }

    public Project findPro(String id){
        Project poj = projectDao.findPro(id);
        //查询父级任务类型
        String sql = "select code,name from demo_project_type pt where code = ?";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,poj.getParent_code());
        if (list.size()>0){
            poj.setParent_name(String.valueOf(list.get(0).get("name")));
        }
        return poj;
    }

    /**
     * 根据用户ID获取用户任务（执行状态）
     * @param userId
     * @return
     */
    public List<Project> findPojByUserId(String userId, String state){
        return projectDao.findPojByUserId(userId,state);
    }


    public List<ProjectType> findAllTopPojType(){
        return projectTypeDao.findAllTopPojType();
    }


    public List<ProjectType> findSubPojType(String pojTypeCode){
        return projectTypeDao.findSubPojType(pojTypeCode);
    }

}
