package com.epes.demo.controller;

import com.epes.demo.entity.PerformanceScore;
import com.epes.demo.service.PerformanceScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:07:26
* @ClassName 类名称：
* @Description 类描述：
*/
@Controller
@RequestMapping(value = "/score")
public class ScoreController {

    private final PerformanceScoreService scoreService;

    @Autowired
    public ScoreController(PerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(value = "/addScore")
    @ResponseBody
    public int addScpre(PerformanceScore score){
        return scoreService.addScore(score);
    }

    @PostMapping(value = "/showScore")
    @ResponseBody
    public List<PerformanceScore> showScore(String userid){
        return scoreService.findScoreByEmpid(userid);
    }

}
