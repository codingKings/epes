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
@Table(name = "demo_user_role")
public class UserRole extends BaseEntity {

    @Column(name = "userid",type = MySqlTypeConstant.VARCHAR, isNull = false)
    private String userid;

    @Column(name = "roleid", type = MySqlTypeConstant.VARCHAR, isNull = false)
    private String roleid;

    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

}
