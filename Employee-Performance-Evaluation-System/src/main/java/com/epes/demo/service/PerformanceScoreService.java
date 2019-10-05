package com.epes.demo.service;

import com.epes.demo.dao.ScoreDao;
import com.epes.demo.entity.PerformanceScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:01:36
* @ClassName 类名称：
* @Description 类描述：
*/
@Service
public class PerformanceScoreService {

    private final BaseService baseService;
    private final ScoreDao scoreDao;


    @Autowired
    public PerformanceScoreService(BaseService baseService, ScoreDao scoreDao) {
        this.baseService = baseService;
        this.scoreDao = scoreDao;
    }

    /**
     * 绩效评分
     * @param performanceScore
     * @return
     */
    @Transactional
    public Map<String,String> addScore(PerformanceScore performanceScore){
        Map<String, String> result = new HashMap<>(0);
        performanceScore.setId(UUID.randomUUID().toString());
        int i = baseService.insert(performanceScore);
        if (i>0){
            result.put("success","success");
            result.put("msg","评分成功");
        }else {
            result.put("success","error");
            result.put("msg","评分失败,无法连接数据库");
        }
        return result;
    }

    /** 查看评分
     * @param userid
     * @return
     */
    public List<PerformanceScore> findScoreByEmpid(String userid){
        return scoreDao.findScoreByEmpid(userid);
    }
}
