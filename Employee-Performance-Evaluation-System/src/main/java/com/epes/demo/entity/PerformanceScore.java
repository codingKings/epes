package com.epes.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午10:11:55
* @ClassName 类名称：
* @Description 类描述：
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "demo_performance_score")
public class PerformanceScore extends BaseEntity{

    @Column(name = "id", type = MySqlTypeConstant.CHAR, isKey = true, length = 36)
    private String id ;

    @Column(name = "startdate", type = MySqlTypeConstant.DATETIME)
    private String startdate;

    @Column(name = "enddate", type = MySqlTypeConstant.DATETIME)
    private String enddate;

    @Column(name = "score", type = MySqlTypeConstant.INT,isNull = false)
    private String score;

    @Column(name = "review", type = MySqlTypeConstant.VARCHAR)
    private String review;


    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

    /**
     * 关联被评人
     */
    @Column(name = "empid", type = MySqlTypeConstant.VARCHAR)
    private String empid;

    private String empName;

    /**
     * 关联评分人
     */
    @Column(name = "leaderid", type = MySqlTypeConstant.VARCHAR)
    private String leaderid;

    private String leaderName;
}
