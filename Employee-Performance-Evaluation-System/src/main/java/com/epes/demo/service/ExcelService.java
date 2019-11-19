package com.epes.demo.service;

import com.epes.demo.dao.ExcelDao;

import com.epes.demo.entity.Excels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelService {
    private final ExcelDao excelDao;

    @Autowired
    public ExcelService(ExcelDao excelDao){
        this.excelDao = excelDao;
    }

    public List<Excels> QueryExcel(){
        return excelDao.queryExcel();
    }
}
