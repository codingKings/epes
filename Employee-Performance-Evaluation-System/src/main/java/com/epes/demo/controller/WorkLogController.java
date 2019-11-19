package com.epes.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.epes.demo.entity.PerformanceScore;
import com.epes.demo.entity.UserInfo;
import com.epes.demo.entity.WorkLog;
import com.epes.demo.service.BaseService;
import com.epes.demo.service.PerformanceScoreService;
import com.epes.demo.service.UserInfoService;
import com.epes.demo.service.WorkLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 程龙
 * @version 创建时间：2019年10月1日 下午2:07:26
 * @ClassName 类名称：
 * @Description 类描述：
 */

@Controller
@RequestMapping(value = "/worklog")
public class WorkLogController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WorkLogService workLogService;
    private final BaseService baseService;
    private final UserInfoService userInfoService;
    private final PerformanceScoreService scoreService;
    private final JdbcTemplate jdbcTemplate;



    @Autowired
    public WorkLogController(WorkLogService workLogService, BaseService baseService, UserInfoService userInfoService, PerformanceScoreService scoreService, JdbcTemplate jdbcTemplate) {
        this.workLogService = workLogService;
        this.baseService = baseService;
        this.userInfoService = userInfoService;
        this.scoreService = scoreService;
        this.jdbcTemplate = jdbcTemplate;
    }

  /*  @PostMapping(value = "/showLog")
    @ResponseBody
    public List<WorkLog> showLog(String userid, String pojid, String startdate, String enddate){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        if ( startdate == null || "".equals(startdate)){
            startdate = sdf.format(date);
        }
        if (enddate == null || "".equals(enddate)){
            Calendar curr = Calendar.getInstance();
            curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1);
            date = curr.getTime();
            enddate = sdf.format(date);
        }
        return workLogService.findByUserId(userid,startdate,enddate,pojid);
    }*/

    @PostMapping(value = "/findLogOnUser")
    @ResponseBody
    public List<WorkLog> findLogOnUser(String userid, String pojid, String startdate, String enddate) {
        Date date = new Date();
        Calendar curr = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        // 判断查询结束日期是否为空
        if (enddate == null || "".equals(enddate)) {
            // 结束日期如果为空，则获取当日日期+1
            curr.add(Calendar.DATE, 1);
        } else {
            try {
                //结束日期不为空，则
                date = sdf.parse(enddate);
                curr.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        date = curr.getTime();
        enddate = sdf.format(date);

        if (startdate == null || "".equals(startdate)) {
            //如果开始时间为空，赋初始值
            startdate = "1996-01-01";
        }
        //判断开始时间是否大于结束时间，大于结束时间则调换
        int res = startdate.compareTo(enddate);
        if (res > 0) {
            String src = enddate;
            enddate = startdate;
            startdate = src;
        }
        // 查询员工日志
        return workLogService.findByUserId(userid, pojid, startdate, enddate);
    }

    @PostMapping(value = "/findLogByid")
    @ResponseBody
    public WorkLog findLogById(String id) {
        return workLogService.findById(id);
    }

    /*
     * @PostMapping(value = "/findLogsByPojOfUser")
     * 
     * @ResponseBody
     * public JSONObject findLogsByPojOfUser(String logDate,String userid,@RequestParam(value =
     * "pojids[]")String[] pojids) {
     * JSONObject result = new JSONObject();
     * try {
     * if (pojids!=null&&pojids.length>0){
     * StringBuilder sql = new StringBuilder();
     * sql.append("select id,pojid,progress,question,logDate from demo_worklog log ");
     * Object[] param = new Object[pojids.length+2];
     * sql.append("where log.pojid in(");
     * for (int i=0; i<pojids.length;i++){
     * sql.append("?");
     * if (i!=pojids.length-1){
     * sql.append(",");
     * }
     * param[i] = pojids[i];
     * }
     * sql.append(") and logDate = ? and log.userid = ? order by logDate asc");
     * param[pojids.length] = logDate;
     * param[pojids.length+1] = userid;
     * List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(),param);
     * result.put("data",list);
     * 
     * }
     * }catch (Exception e){
     * logger.error(e.getMessage());
     * }
     * return result;
     * }
     */
    
    @PostMapping(value = "/findLogsByPojOfUser")
    @ResponseBody
    public List<Map<String,String>> findLogsByPojOfUser(String userid,String logDate){
        //获取当月该登录用户下的所有任务日志
        List<WorkLog> listLog = workLogService.finLogsByPojOfUser(userid,logDate);
        //把每个任务的日志封装到map集合里，再把每个任务的对象封装到list集合里
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        Map<String,String> mapLog = null;
        for(WorkLog log : listLog) {
            mapLog=new HashMap<String,String>();
            mapLog.put("pojid", log.getPojid());
            mapLog.put("progress", log.getProgress());
            mapLog.put("question", log.getQuestion());
            list.add(mapLog);
            mapLog=null;
        }
        
        return list;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String, String> update(WorkLog workLog) {
        return workLogService.update(workLog);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(String id) {
        return workLogService.delete(id);
    }



    @PostMapping(value = "/addLogs")
    @ResponseBody
    @Transactional
    public JSONObject addLogs(String userid, @RequestParam(value = "pojids[]") String[] pojids,
                              @RequestParam(value = "pojnames[]") String[] pojnames,
                              @RequestParam(value = "progress[]") String[] progress,
                              @RequestParam(value = "questions[]",required = false) String[] questions
//            ,JSONArray progressScores,JSONArray qualityScores
    ) {
        
        JSONObject result = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        try {
            UserInfo user = userInfoService.findUser(userid);
            for (int i = 0; i < pojids.length; i++) {
                WorkLog workLog = new WorkLog();
                workLog.setPojid(pojids[i]);
                workLog.setPojname(pojnames[i]);
                workLog.setUserid(user.getId());
                workLog.setUsernmae(user.getName());
                workLog.setProgress(progress[i]);
                if(questions==null||questions.length==0) {
                    workLog.setQuestion("");
                }else {
                    
                    workLog.setQuestion(questions[i]);
                }

                workLog.setLogDate(df.format(new Date()));
                //保存自评
//            PerformanceScore score = new PerformanceScore();
//            score.setType("0");
//            score.setProgressScore(progressScores[i]);
//            score.setQualityScore(qualityScores[i]);
//            score.setUserid(user.getId());
//            score.setUsername(user.getName());
                //获取当前月份
                String nowDate = df.format(new Date());
                //查询是否有日志
                WorkLog oldLog = workLogService.findByData(pojids[i], userid, nowDate);

                if (oldLog == null) {
                    //新增
                    workLog.setId(String.valueOf(UUID.randomUUID()));
//                score.setLogid(workLog.getId());
//                scoreService.addScore(score);
                    workLogService.addWorkLog(workLog);
                } else {
                    assert false;
                    workLog.setId(oldLog.getId());
//                score.setLogid(workLog.getId());
                    //修改
//                scoreService.findScoreByUserOfLog(userid,workLog.getId());
//                scoreService.update(score);
                    workLogService.update(workLog);
                }
            }
            result.put("state","200");
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.put("state","300");
            result.put("error",e.getMessage());
        }
        return result;
    }
}
