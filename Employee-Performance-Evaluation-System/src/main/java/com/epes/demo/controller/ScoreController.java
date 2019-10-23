package com.epes.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.epes.demo.entity.PerformanceScore;
import com.epes.demo.entity.Project;
import com.epes.demo.entity.UserInfo;
import com.epes.demo.service.PerformanceScoreService;
import com.epes.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Perf;

import java.text.SimpleDateFormat;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PerformanceScoreService scoreService;
    private final UserInfoService userInfoService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScoreController(PerformanceScoreService scoreService, UserInfoService userInfoService, JdbcTemplate jdbcTemplate) {
        this.scoreService = scoreService;
        this.userInfoService = userInfoService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping(value = "/addScore")
    @ResponseBody
    @Transactional
    public JSONObject addScpre(String userid, String logUserid, @RequestParam(value = "pojids[]") String[] pojids,
                               @RequestParam(value = "pojnames[]") String[] pojnames,
                               @RequestParam(value = "progressScore[]") String[] progressScore,
                               @RequestParam(value = "qualityScore[]") String[] qualityScore,
                               String professionScore, String dedicationScore,
                               String dutyScore, String businessScore,
                               String innovationScore, String teamScore,
                               String logDate){
        JSONObject result = new JSONObject();
        try{
            UserInfo user = userInfoService.findUser(userid);
            for (int i = 0; i < pojids.length;i++){
                String pojid = pojids[i];
                PerformanceScore score = new PerformanceScore();
                score.setUsername(user.getName());
                score.setUserid(userid);
                score.setQualityScore(qualityScore[i]);
                score.setProgressScore(progressScore[i]);
                score.setPojid(pojid);
                score.setScoreUserid(logUserid);
                score.setScoreDate(logDate);
                score.setType("0");
                //查询是否存同一评价
                //根据被评人、评价人、项目ID和日期查询
                PerformanceScore p = scoreService.findScoreByUserPojId(logDate,logUserid,userid,pojid);
                if (p!=null){
                    score.setId(p.getId());
                    scoreService.update(score);
                } else {
                    scoreService.addScore(score);
                }

            }

            PerformanceScore score = new PerformanceScore();
            score.setUsername(user.getName());
            score.setUserid(userid);
            score.setBusinessScore(businessScore);
            score.setProfessionScore(professionScore);
            score.setDedicationScore(dedicationScore);
            score.setDutyScore(dutyScore);
            score.setInnovationScore(innovationScore);
            score.setTeamScore(teamScore);
            score.setScoreUserid(logUserid);
            score.setScoreDate(logDate);
            score.setType("1");
            //查询是否存同一评价
            //根据被评人、评价人、项目ID和日期查询
            PerformanceScore p = scoreService.findScoreByUser(logDate,logUserid,userid);
            if (p!=null){
                score.setId(p.getId());
                scoreService.update(score);
            } else {
                scoreService.addScore(score);
            }
            result.put("state","200");
        }catch (Exception e){
            logger.error(e.getMessage());
            result.put("state","300");
            result.put("error",e.getMessage());
        }

        return result;
    }

    @PostMapping(value = "/showScore")
    @ResponseBody
    public List<PerformanceScore> showScore(String userid){
        return scoreService.findScoreByEmpid(userid);
    }

    /**
     * 获取项目用于评价
     * @param userid
     * @param date
     * @return
     */
    @PostMapping(value = "/findPojToScore")
    @ResponseBody
    public List<Map<String,Object>> findPojToScore(String userid,String date){

        String sql ="select p.*,a.*,pt.code ptcode,pt.parent_code,pt.name ptname from demo_project p " +
                "  left join (select log.id logid,log.pojid,log.question,log.progress from demo_worklog log where logDate = ?) a on p.id = a.pojid " +
                " left join demo_project_type pt on p.sub_type_code = pt.code " +
                "where date_format(startdate,'yyyy-MM') <= date_format(sysdate(),'yyyy-MM') and date_format(enddate,'yyyy-MM') >= date_format(sysdate(),'yyyy-MM')  and p.state = '01' and p.userId = ?";
        List<Map<String,Object>> projectList = jdbcTemplate.queryForList(sql,date,userid);
        String findPojType = "select code,name from demo_project_type";
        List<Map<String,Object>> pojTypeList = jdbcTemplate.queryForList(findPojType);
        //将任务种类封装到Map里面，方便查询；
        Map<String,String> pojTypeMapper = new HashMap<>();
        for (Map<String,Object> typeMap : pojTypeList) {
            String key = String.valueOf(typeMap.get("code"));
            String value = String.valueOf(typeMap.get("name"));
            pojTypeMapper.put(key,value);
        }
        //处理日期字段
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> poj : projectList){
            poj.put("parent_name",pojTypeMapper.get(String.valueOf(poj.get("parent_code"))));
            Date startDate = (Date) poj.get("startdate");
            Date endDate = (Date) poj.get("enddate");
            if (startDate != null){
                poj.put("startdate",df.format(startDate));
            }

            if (endDate != null ){
                poj.put("enddate",df.format(endDate));
            }
        }

        return projectList;
    }

}
