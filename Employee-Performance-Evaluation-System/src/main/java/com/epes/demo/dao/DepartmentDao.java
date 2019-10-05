package com.epes.demo.dao;

import com.epes.demo.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/
@Mapper
public interface DepartmentDao {

    @Select("select * from demo_department where id = #{id}")
    Department findDept(String id);

    @Select("select * from demo_department ")
    List<Department> findAllDept();

    @Select("select d.id,d.name,d.code,d.createtime,d.modifiedtime,u.id,u.name,u.phone,u.code " +
            "from demo_department d" +
            "left join demo_user_info u " +
            "on d.userid = u.id")
    List<Department> findAllDeptList();



}
