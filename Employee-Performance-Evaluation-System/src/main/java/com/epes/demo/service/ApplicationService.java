package com.epes.demo.service;

import com.epes.demo.dao.ApplicationDao;
import com.epes.demo.entity.Application;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:01:36
* @ClassName 类名称：
* @Description 类描述：
*/

@Service
public class ApplicationService {

    private final BaseService baseService;
    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationService(BaseService baseService, ApplicationDao applicationDao) {
        this.baseService = baseService;
        this.applicationDao = applicationDao;
    }

    public Application findAppById(String id){
       return applicationDao.findAppById(id);
   }
}
