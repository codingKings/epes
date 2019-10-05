package com.epes.demo.dao;

import com.epes.demo.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
}
