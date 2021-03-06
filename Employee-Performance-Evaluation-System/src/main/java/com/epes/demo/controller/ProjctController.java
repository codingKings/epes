package com.epes.demo.controller;

import com.epes.demo.entity.Department;
import com.epes.demo.entity.Project;
import com.epes.demo.entity.ProjectType;
import com.epes.demo.service.BaseService;
import com.epes.demo.service.DepartmentService;
import com.epes.demo.service.ProjectService;
import com.epes.demo.tool.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 程龙
* @version 创建时间：
* @ClassName 类名称：
* @Description 类描述：
*/

@Controller
@RequestMapping(value = "/projct")
public class ProjctController {

    private final BaseService baseService;
    private final ProjectService projectService;
    private final DepartmentService deptService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjctController(BaseService baseService, ProjectService projectService, DepartmentService deptService, JdbcTemplate jdbcTemplate) {
        this.baseService = baseService;
        this.projectService = projectService;
        this.deptService = deptService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping(value = "/findAll")
    @ResponseBody
    public List<Map<String, Object>> findAll(int pageIndex, int size, SearchParams searchMap){
        PageRequest pageRequest = new PageRequest(pageIndex,size);
        List<Map<String,Object>> list = baseService.pageFindByCondition(Project.class,pageRequest,searchMap);
        //处理日期字段
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String,Object> map : list) {
            Date startDate = (Date) map.get("startdate");
            Date endDate = (Date) map.get("enddate");
            if (startDate != null){
                map.put("startdate",df.format(startDate));
            }

            if (endDate != null ){
                map.put("enddate",df.format(endDate));
            }

        }
        return list;
    }

    @PostMapping(value = "/disabled")
    @ResponseBody
    public Map<String,String> disabled(String id,int dr){
        Project project = new Project();
        if (dr == 1){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            project.setEnddate(df.format(new Date()));
        }
        project.setId(id);
        project.setDr(dr);
        return  projectService.update(project);
    }

    @PostMapping(value = "findPojById")
    @ResponseBody
    public Project findPojById(String id){
        return projectService.findPro(id);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Map<String ,String> delete(String id){
        return projectService.delete(id);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String,String> update(Project project){
        return projectService.update(project);
    }

    /**
     * 修改状态
     * @param pojid
     * @param state
     * @return
     */
    @PostMapping(value = "/updatePojState")
    @ResponseBody
    public Map<String,String> updatePojState(String pojid,String state){
        Project project = new Project();
        project.setId(pojid);
        project.setState(state);
        return projectService.update(project);
    }

    /**
     * 审批意见
     * @param project
     * @return
     */
    @PostMapping(value = "/updatePojText")
    @ResponseBody
    public Map<String,String> updatePojText(String pojid,String state,String option){
        Project project = new Project();
        project.setId(pojid);
        project.setState(state);
        project.setComments(option);;
        
        return projectService.updateText(project);
    }
    
    @PostMapping(value = "/addPoj")
    @ResponseBody
    public Map<String , String > addPoj(Project project){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        switch (project.getType()) {
            case Project.YEAR :{
                //年度任务 期限为一年
                Date date = new Date();
                project.setStartdate(df.format(date));
                //结束时间为一年后
                //设置起时间
                cal.setTime(date);
                cal.add(Calendar.MONTH,12);
                project.setEnddate(df.format(cal.getTime()));
            } break;
            case Project.MONTH :{
                //月度任务 期限为本月
                Date date = new Date();
                cal.setTime(date);
                cal.set(Calendar.DATE, 1);
                project.setStartdate(df.format(date));
//                cal.add(Calendar.MONTH,1);
                //日期回滚一天，也就是最后一天
                cal.roll(Calendar.DATE, -1);
                project.setEnddate(df.format(cal.getTime()));
            } break;
            case Project.INTERIM :break;
            default:
        }
        return projectService.addPorject(project);
    }

    @PostMapping(value = "/findPojByDept")
    @ResponseBody
    public List<Map<String, Object>> findPojByDept(SearchParams searchMap){
        PageRequest pageRequest = new PageRequest(0,999);
        return baseService.pageFindByCondition(Project.class,pageRequest,searchMap);
    }

    @PostMapping(value = "/findPojByUserId")
    @ResponseBody
    public List<Project> findPojByUserId(String userId,String state){
        //查询所有任务种类(如果有缓存机制，请放到缓存里面)
        String findPojType = "select code,name from demo_project_type";
        List<Map<String,Object>> pojTypeList = jdbcTemplate.queryForList(findPojType);
        //将任务种类封装到Map里面，方便查询；
        Map<String,String> pojTypeMapper = new HashMap<>();
        for (Map<String,Object> typeMap : pojTypeList) {
            String key = String.valueOf(typeMap.get("code"));
            String value = String.valueOf(typeMap.get("name"));
            pojTypeMapper.put(key,value);
        }
        List<Project> projectList =projectService.findPojByUserId(userId,state);
        for (Project poj : projectList){
            poj.setParent_name(pojTypeMapper.get(poj.getParent_code()));
        }
        return projectList;
    }


    /**
     * 查找所有一级任务种类
     * @return
     */
    @GetMapping(value = "/findAllTopPojType")
    @ResponseBody
    public List<ProjectType> findAllTopPojType(){
        return projectService.findAllTopPojType();
    }

    /**
     * 查找子任务种类
     * @param code
     * @return
     */
    @GetMapping(value = "/findSubPojType")
    @ResponseBody
    public List<ProjectType> findSubPojType(String code){
        return projectService.findSubPojType(code);
    }
}
