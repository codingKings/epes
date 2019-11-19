package com.epes.demo.dao;

import com.epes.demo.entity.Excels;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelDao {
    List<Excels> queryExcel();
}
