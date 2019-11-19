package com.epes.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class PojExcel {
    private String jid;

    //private String name;
    @Excel(name = "任务分类" ,orderNum = "2",width = 30)
    private String taskname;
    @Excel(name = "目标要求" ,orderNum = "4",width = 30)
    private String content;
    @Excel(name = "年度任务" ,orderNum = "3",width = 30)
    private String demand;
    @Excel(name = "进展情况" ,orderNum = "5",width = 40)
    private String progress;
    @Excel(name= "问题及原因",orderNum = "6",width = 30)
    private String question;

    private String progressScore;

    private String qualityScore;
    @Excel(name= "进度提前",orderNum = "6",width = 8)
    private String progressScoreNumber0;
    @Excel(name= "进度正常",orderNum = "6",width = 8)
    private String progressScoreNumber1;
    @Excel(name= "进度滞后",orderNum = "6",width = 8)
    private String progressScoreNumber2;
    @Excel(name= "质量好",orderNum = "6",width = 8)
    private String qualityScore0;
    @Excel(name= "质量中",orderNum = "6",width = 8)
    private String qualityScore1;
    @Excel(name= "质量差",orderNum = "6",width = 8)
    private String qualityScore2;

}
