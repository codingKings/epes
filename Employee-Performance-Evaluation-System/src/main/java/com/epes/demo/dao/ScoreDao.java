package com.epes.demo.dao;

import com.epes.demo.entity.PerformanceScore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 程龙
 * @version 创建时间：2019年10月1日 上午11:26:03
 * @ClassName 类名称：
 * @Description 类描述：
 */
@Mapper
public interface ScoreDao {

    @Select("select * from demo_performance_score where userid = #{userid}")
    List<PerformanceScore> findScoreByEmpid(@Param("userid") String userid);

    @Select("select * from demo_performance_score where userid = #{userid} and logid = #{logid}")
    PerformanceScore findScoreByUserOfLog(@Param("userid") String userid, @Param("logid") String logid);
}