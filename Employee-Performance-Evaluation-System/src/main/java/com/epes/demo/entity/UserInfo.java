package com.epes.demo.entity;


import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
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
@Table(name = "demo_user_info")
public class UserInfo extends BaseEntity implements Serializable {
    public final static String CODE_TITLE = "UX";

    @Column(name = "id", type = MySqlTypeConstant.CHAR, isKey = true, length = 36)
    private String id;

    @Column(name = "code",type = MySqlTypeConstant.VARCHAR)
    private String code;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String name;

    @Column(name = "loginname", type = MySqlTypeConstant.VARCHAR, length = 36,isNull = false)
    private String loginName;

    @Column(name = "sex", type = MySqlTypeConstant.CHAR,length = 2)
    private String sex;

    @Column(name = "age",type = MySqlTypeConstant.INT, length = 2)
    private Integer age;

    @Column(name = "phone", type = MySqlTypeConstant.VARCHAR)
    private String phone;

    @Column(name = "address",type = MySqlTypeConstant.VARCHAR)
    private String address;

    /**
     * 关联部门表
     */
    @Column(name = "deptid", type = MySqlTypeConstant.VARCHAR)
    private String deptid;

    /**
     * 用于展示部门名称
     */
    private String deptName;

    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

}
