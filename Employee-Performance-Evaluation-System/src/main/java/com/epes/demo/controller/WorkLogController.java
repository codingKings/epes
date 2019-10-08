package com.epes.demo.controller;

import com.epes.demo.entity.PerformanceScore;
import com.epes.demo.entity.UserInfo;
import com.epes.demo.entity.WorkLog;
import com.epes.demo.service.BaseService;
import com.epes.demo.service.PerformanceScoreService;
import com.epes.demo.service.UserInfoService;
import com.epes.demo.service.WorkLogService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private final WorkLogService workLogService;
    private final BaseService baseService;
    private final UserInfoService userInfoService;
    private final PerformanceScoreService scoreService;

    @Autowired
    public WorkLogController(WorkLogService workLogService, BaseService baseService, UserInfoService userInfoService, PerformanceScoreService scoreService) {
        this.workLogService = workLogService;
        this.baseService = baseService;
        this.userInfoService = userInfoService;
        this.scoreService = scoreService;
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
    public List<WorkLog> findLogOnUser(String userid, String pojid, String startdate, String enddate){
        Date date = new Date();
        Calendar curr = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        // 判断查询结束日期是否为空
        if ( enddate == null || "".equals(enddate)){
            // 结束日期如果为空，则获取当日日期+1
            curr.add(Calendar.DATE,1);
        } else {
            try {
                //结束日期不为空，则
                date = sdf.parse(enddate);
                curr.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        date=curr.getTime();
        enddate = sdf.format(date);

        if (startdate == null || "".equals(startdate)){
            //如果开始时间为空，赋初始值
            startdate = "1996-01-01";
        }
        //判断开始时间是否大于结束时间，大于结束时间则调换
        int res=startdate.compareTo(enddate);
        if(res>0){
            String src = enddate;
            enddate = startdate;
            startdate = src;
        }
        // 查询员工日志
        return workLogService.findByUserId(userid,pojid,startdate,enddate);
    }

    @PostMapping(value = "/findLogByid")
    @ResponseBody
    public WorkLog findLogById(String id){
        return workLogService.findById(id);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String,String> update(WorkLog workLog){
        return workLogService.update(workLog);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Map<String,String> delete(String id){
        return workLogService.delete(id);
    }

    @PostMapping(value = "/addLog")
    @ResponseBody
    public Map<String,String> addLog(String userid,String pojid,String pojname,String progress,String question,String progressScore,String qualityScore){

        Map<String,String> result = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

        UserInfo user = userInfoService.findUser(userid);
        WorkLog workLog = new WorkLog();

        workLog.setPojid(pojid);
        workLog.setPojname(pojname);
        workLog.setUserid(user.getId());
        workLog.setUsernmae(user.getName());
        workLog.setProgress(progress);
        workLog.setQuestion(question);

        workLog.setLogDate(df.format(new Date()));
        //保存自评
        PerformanceScore score = new PerformanceScore();
        score.setType("0");
        score.setProgressScore(progressScore);
        score.setQualityScore(qualityScore);
        score.setUserid(user.getId());
        score.setUsername(user.getName());


        //获取当前月份
        String nowDate = df.format(new Date());
        //查询是否有日志
        WorkLog oldLog =  workLogService.findByData(pojid,nowDate);

        if (oldLog == null){
            //新增
            workLog.setId(String.valueOf(UUID.randomUUID()));
            score.setLogid(workLog.getId());
            scoreService.addScore(score);
            result = workLogService.addWorkLog(workLog);
        } else {
            assert false;
            workLog.setId(oldLog.getId());
            score.setLogid(workLog.getId());
            //修改
            scoreService.findScoreByUserOfLog(userid,workLog.getId());
            scoreService.update(score);
            result = workLogService.update(workLog);
        }

        return result;
    }
}
