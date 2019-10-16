package com.epes.demo.controller;

import com.epes.demo.entity.Department;
import com.epes.demo.service.DepartmentService;
import com.epes.demo.tool.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:57:21
* @ClassName 类名称：
* @Description 类描述：
*/
@RequestMapping(value = "/dept")
@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/findAllDept")
    @ResponseBody
    public List<Department> findAllDept(){
        return departmentService.findAllDept();
    }

    @PostMapping(value = "/findDeptBypage")
    @ResponseBody
    public List<Map<String,Object>> findDeptBypage(SearchParams searchParams){
        PageRequest pageRequest = new PageRequest(0,999);
        return departmentService.findAllByPage(pageRequest,searchParams);
    }

    @PostMapping(value = "/disabled")
    @ResponseBody
    public Map<String,String> disabled(String id,int dr){
        Department department = new Department();
        department.setId(id);
        department.setDr(dr);
        return departmentService.update(department);
    }

    @PostMapping(value = "/updateDept")
    @ResponseBody
    public Map<String,String> updateDept(Department department){
        return departmentService.update(department);
    }

    @PostMapping(value = "/findDeptById")
    @ResponseBody
    public Department findDeptById(String id){
        return departmentService.findDeptById(id);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Map<String , String> delete(String id){
        return departmentService.delete(id);
    }

    @PostMapping(value = "/addDept")
    @ResponseBody
    public Map<String, String> addDept(Department department){
        return departmentService.addDepartment(department);
    }

    @GetMapping(value = "/findDeptUsers")
    @ResponseBody
    public List<Map<String, Object>> findDeptUsers(String id){
        String sql = "select u.id,u.name,d.name deptName from demo_user_info u left join demo_department d on u.deptid = d.id where deptid = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{id});
        return list;
    }
}
