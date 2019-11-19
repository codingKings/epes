package com.epes.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.*;

import java.util.List;

@Data
public
class Excels {
    private String id;

    @Excel(name = "负责人", width = 15,orderNum = "2",needMerge = true)
    private String name;

    @ExcelCollection(name = "任务信息", orderNum = "3")
    private List<PojExcel> prijectList;


}
