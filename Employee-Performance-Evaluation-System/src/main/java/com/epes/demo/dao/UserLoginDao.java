package com.epes.demo.dao;

import com.epes.demo.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/
@Mapper
public interface UserLoginDao {

    @Update("update demo_user_login set password=#{password},modifiedtime=${modifiedtime} where id = #{id}")
    int updatePassword(UserLogin userLogin);

    @Select("select password from demo_user_login where  id= #{id}")
    String findUserId(String id);
}
