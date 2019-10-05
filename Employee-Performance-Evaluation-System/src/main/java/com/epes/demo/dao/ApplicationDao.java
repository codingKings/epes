package com.epes.demo.dao;

import com.epes.demo.entity.Application;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.*;
/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/
@Mapper
public interface ApplicationDao {

    @Select("select * from demo_application where id = #{id} order by createtime")
    Application findAppById(String id);
}
