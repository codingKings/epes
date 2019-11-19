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

    @Select("select * from demo_performance_score where scoreDate= #{scoreDate} and type = '0' and scoreUserid = #{scoreUserid} and userid = #{userid} and pojid = #{pojid} ")
    PerformanceScore findScoreByUserPojid(@Param("scoreDate") String scoreDate,@Param("scoreUserid")String scoreUserid,
                                            @Param("userid") String userid, @Param("pojid") String pojid);

    @Select("select * from demo_performance_score where scoreDate= #{scoreDate} and type = '1' and scoreUserid = #{scoreUserid} and userid = #{userid} ")
    PerformanceScore findScoreByUser(@Param("scoreDate") String scoreDate,@Param("scoreUserid")String scoreUserid,
                                            @Param("userid") String userid);

    @Select("select * from demo_performance_score where scoreDate = #{date} and scoreUserid=#{userid} and userid = #{user}")
    List<PerformanceScore> findScoreById(@Param("user")String user, @Param("userid")String userid, @Param("date")String date);

    @Select("SELECT pojid, progressScore ,qualityScore FROM demo_performance_score WHERE pojid = #{pojid} AND scoreDate " + 
        "BETWEEN #{startDate} AND #{endDate} AND userid = #{userid} AND progressScore IS NOT NULL ORDER BY SCOREdate ASC ")
    List<PerformanceScore> getSelfProgress(@Param("pojid") String pojid,@Param("userid")String userid,@Param("startDate") String startDate,@Param("endDate") String endDate);
}