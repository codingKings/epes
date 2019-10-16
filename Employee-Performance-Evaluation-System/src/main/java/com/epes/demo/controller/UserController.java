package com.epes.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.epes.demo.entity.Department;
import com.epes.demo.entity.UserInfo;
import com.epes.demo.entity.UserLogin;
import com.epes.demo.service.DepartmentService;
import com.epes.demo.service.UserInfoService;
import com.epes.demo.service.UserLoginService;
import com.epes.demo.tool.Encryption;
import com.epes.demo.tool.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:07:26
* @ClassName 类名称：
* @Description 类描述：
*/
@Controller
@RequestMapping(value = "/UserInfo")
public class UserController {

    private final UserInfoService userInfoService;
    private final UserLoginService userLoginService;
    public final DepartmentService departmentService;

    @Autowired
    public UserController(UserInfoService userInfoService, UserLoginService userLoginService, DepartmentService departmentService) {
        this.userInfoService = userInfoService;
        this.userLoginService = userLoginService;
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/findAllUser")
    @ResponseBody
    public List<UserInfo> findAllUser(){
        return userInfoService.findAllUsers();
    }

    @PostMapping(value = "/findUserBypage")
    @ResponseBody
    public List<Map<String, Object>> findUserBypage(int pageIndex, int size, SearchParams search){
        PageRequest pageRequest = new PageRequest(pageIndex,size);
        List<Map<String, Object>> result = userInfoService.findAllUsers(pageRequest, search);
        List<Department> depts = departmentService.findAllDept();
        for (Map<String, Object> user : result){
            String deptid = user.get("deptid").toString();
            for (int i = 0; i<depts.size();i++){
                if (deptid.equals(depts.get(i).getId())){
                    user.put("deptName", depts.get(i).getName());
                }
            }
        }

        return result;
    }

    @PostMapping(value = "/resetPassword")
    @ResponseBody
    public Map<String,String> resetPassword(String id){
        String msg = userLoginService.resetPassword(id);
        Map<String,String> data = new HashMap<>(0);
        data.put("msg",msg);
        return data;
    }

    @PostMapping(value = "/deleteUser")
    @ResponseBody
    public Map<String, String> deleteUser(String id){
        return userInfoService.deleteUser(id);
    }

    @PostMapping(value = "/findUserById")
    @ResponseBody
    public UserInfo findUserById(String id){
        return userInfoService.findUser(id);
    }

    @PostMapping(value = "/updateUserinfo")
    @ResponseBody
    public Map<String, String> updateUserinfo(UserInfo request){
        return userInfoService.updataUser(request);
    }

    @PostMapping(value = "/addUser")
    @ResponseBody
    public Map<String, String> addUser(UserInfo request){
        return userInfoService.addUser(request);
    }
}
