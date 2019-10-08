package com.epes.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
@Table(name = "demo_worklog")
public class WorkLog extends BaseEntity{

    @Column(name = "id", type = MySqlTypeConstant.CHAR, isKey = true, length = 36)
    private String id;

    /**
     * 进展情况
     */
    @Column(name = "progress", type = MySqlTypeConstant.TEXT, isNull = false)
    private String progress;

    /**
     * 问题及原因
     */
    @Column(name = "question", type = MySqlTypeConstant.TEXT, isNull = false)
    private String question;

    @Column(name = "logDate", type = MySqlTypeConstant.VARCHAR)
    private String logDate;

    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

    /**
     * 关联用户表
     */
    @Column(name = "userid", type = MySqlTypeConstant.CHAR, isNull = false)
    private String userid;

    private String usernmae;

    /**
     * 关联项目表
     */
    @Column(name = "pojid", type = MySqlTypeConstant.CHAR, isNull = false)
    private String pojid;

    private String pojname;

}
