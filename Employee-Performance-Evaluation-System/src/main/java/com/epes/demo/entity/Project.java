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
@Table(name = "demo_project")
public class Project extends BaseEntity {

    //临时任务
    public static final String INTERIM = "2";
    //月度任务
    public static final String MONTH = "1";
    //年度任务
    public static final String YEAR = "0";

    @Column(name = "id", type = MySqlTypeConstant.CHAR, isKey = true, length = 36)
    private String id;

    @Column(name = "name", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String name;

    @Column(name = "code", type = MySqlTypeConstant.VARCHAR)
    private String code;

    @Column(name = "startdate", type = MySqlTypeConstant.DATETIME)
    private String startdate;

    @Column(name = "enddate", type = MySqlTypeConstant.DATETIME)
    private String enddate;

    @Column(name = "deptid", type = MySqlTypeConstant.VARCHAR)
    private String deptid;

    @Column(name = "deptname", type = MySqlTypeConstant.VARCHAR)
    private String deptname;

    @Column(name = "type", type = MySqlTypeConstant.VARCHAR)
    private String type;



    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    /**
     * 任务所关联的用户
     */
    @Column(name = "userId", type = MySqlTypeConstant.VARCHAR)
    private String userId;

    @Column(name = "userName", type = MySqlTypeConstant.VARCHAR)
    private String userName;


    /**
     * 任务描述
     */
    @Column(name = "content", type = MySqlTypeConstant.TEXT)
    private String content;

    /**
     * 任务要求
     */
    @Column(name = "demand", type = MySqlTypeConstant.TEXT)
    private String demand;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

    /**
     * 任务类型编号
     */
    @Column(name = "sub_type_code", type = MySqlTypeConstant.VARCHAR)
    private String sub_type_code;

    /**
     * 任务类型名称
     */
    @Column(name = "sub_type_name", type = MySqlTypeConstant.VARCHAR)
    private String sub_type_name;


    /**
     * 存储父级任务类型
     */
    private String parent_code;
    private String parent_name;
}
