package com.epes.demo.dao;

import com.epes.demo.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/

@Mapper
public interface UserInfoDao {

    @Update("update demo_user_info set deptid = #{deptid} where id = #{id}")
    void UpdateDept(@Param("deptid")String deptid, @Param("id")String id);

    @Select("select * from demo_user_info where id = #{id}")
    UserInfo findUser(String id);

    @Select("select * from demo_user_info where loginname = #{loginName}")
    UserInfo findUserByLoginName(String loginName);

    @Select("select id,name,code from demo_user_info")
    List<UserInfo> findAllUser();

    @Select("SELECT " + 
        "ui.id,ui.name,ui.deptid," + 
        "CASE " + 
        "WHEN EXISTS (" + 
        "SELECT " + 
        "ps.scoreUserid " + 
        "FROM `demo_performance_score` ps " + 
        "WHERE ps.createtime LIKE CONCAT('%',#{curdate},'%') " + 
        "AND ps.userid= #{userId} " + 
        "AND ui.id = ps.`scoreUserid` ) " + 
        "THEN 'Y' " + 
        "ELSE 'N' " + 
        "END 'isScore'" + 
        "FROM demo_user_info ui")
    List<Map<String, Object>> findAllUserIsScore(@Param("userId")String userId,@Param("curdate")String curdate);
}
