package com.epes.demo.dao;

import com.epes.demo.entity.ProjectType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 程龙
 * @version 创建时间：2019年10月10日 上午11:26:03
 * @ClassName 类名称：任务总类Dao
 * @Description 类描述：
 */

@Mapper
public interface ProjectTypeDao {

    /**
     * 查找所有一级任务种类
     * @return
     */
    @Select("select * from demo_project_type where parent_code = '0'")
    List<ProjectType> findAllTopPojType();

    /**
     * 查找子任务种类
     * @param parentCode
     * @return
     */
    @Select("select * from demo_project_type where parent_code = #{parentCode}")
    List<ProjectType> findSubPojType(@Param("parentCode")String parentCode);
}
