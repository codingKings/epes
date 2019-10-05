package com.epes.demo.dao;

import com.epes.demo.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午11:26:03
* @ClassName 类名称：
* @Description 类描述：
*/

@Mapper
public interface RolePermissionDao {

    @Select("select * from demo_role_permission where role_id = #{roleId}")
    List<RolePermission> findRolePerByRid(@Param("roleId")String roleId);
}
