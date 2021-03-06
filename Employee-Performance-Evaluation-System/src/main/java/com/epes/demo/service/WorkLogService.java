package com.epes.demo.service;

import com.epes.demo.dao.WorkLogDao;
import com.epes.demo.entity.Department;
import com.epes.demo.entity.WorkLog;
import com.epes.demo.tool.SearchParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:01:36
* @ClassName 类名称：
* @Description 类描述：
*/

@Service
public class WorkLogService {

    private final BaseService baseService;
    private final WorkLogDao workLogDao;

    @Autowired
    public WorkLogService(BaseService baseService, WorkLogDao workLogDao) {
        this.baseService = baseService;
        this.workLogDao = workLogDao;
    }

    /**
     * 添加日志
     * @param workLog
     * @return
     */
    @Transactional
    public Map<String, String> addWorkLog(WorkLog workLog){
        Map<String, String> result = new HashMap<>(0);
        if (StringUtils.isBlank(workLog.getId())){
            workLog.setId(UUID.randomUUID().toString());
        }

        int p = baseService.insert(workLog);
        if (p>0){
            result.put("success","success");
            result.put("msg","新增成功");
        }else {
            result.put("success","error");
            result.put("msg","新增失败,无法连接数据库");
        }
        return result;
    }

    /**
     * 修改日志
     * @param workLog
     * @return
     */
    @Transactional
    public Map<String, String> update(WorkLog workLog){
        int p = baseService.update(workLog);
        Map<String,String> map = new HashMap<>(0);
        if (p>0){
            map.put("msg","修改成功");
            map.put("success","success");
        }else {
            map.put("msg","数据不存在或数据无法修改");
            map.put("success","error");
        }
        return map;
    }

    /**
     * 删除日志
     * @param id
     * @return
     */
    @Transactional
    public Map<String, String> delete(String id){
        Map<String, String> result = new HashMap<>(0);
        int i = baseService.delete(WorkLog.class,id);
        if (i>0){
            result.put("success","success");
            result.put("msg","删除成功");
        }else {
            result.put("success","error");
            result.put("msg","删除失败,无法连接数据库");
        }
        return result;
    }

    public List<Map<String, Object>> findAllByPage(PageRequest pageRequest, SearchParams searchParams){
        return baseService.pageFindByCondition(WorkLog.class, pageRequest, searchParams);
    }

    public WorkLog findById(String id){
        return workLogDao.findLog(id);
    }

    public List<WorkLog> findByUserId(String userid, String pojid, String startdate, String enddate){
        return workLogDao.findLogByUserId(userid,pojid,startdate,enddate);
    }

    public WorkLog findByData(String pojId,String userid,String date) {
        return workLogDao.findLogByDate(pojId,userid,date);
    }

    
    public List<WorkLog> finLogsByPojOfUser(String userid, String logDate) {
        // TODO Auto-generated method stub
        return workLogDao.finLogsByPojOfUser( userid, logDate);
    }
}
