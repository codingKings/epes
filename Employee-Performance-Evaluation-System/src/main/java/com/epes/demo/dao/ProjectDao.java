package com.epes.demo.dao;

import com.epes.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/

@Mapper
public interface ProjectDao {

    @Update("update demo_project set deptid = #{deptid} where id = #{id}")
    void UpdateDept(@Param("deptid")String deptid, @Param("id")String id);

    @Select("select p.*,pt.parent_code parent_code from demo_project p left join  demo_project_type pt on p.sub_type_code  = pt.code where p.id = #{id}")
    Project findPro(String id);

    @Select("select p.*,pt.parent_code parent_code from demo_project p left join demo_project_type pt on p.sub_type_code = pt.code where p.userId = #{userId} and state = #{state} and date_format(p.enddate,'yyyy-mm') <= date_format(sysdate(),'yyyy-mm') and p.dr = 0 order by p.createtime")
    List<Project> findPojByUserId(@Param("userId")String userId, @Param("state")String state);
}
