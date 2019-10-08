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

    @Column(name = "progressScore", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String progressScore;

    @Column(name = "qualityScore", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String qualityScore;

    /**
     * 类型：0为自评；1为他评
     */
    @Column(name = "type", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String type;

    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private String createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private String modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

    /**
     * 关联日志
     */
    @Column(name = "logid", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String logid;

    /**
     * 关联评分人
     */
    @Column(name = "userid", type = MySqlTypeConstant.VARCHAR)
    private String userid;

    @Column(name = "username", type = MySqlTypeConstant.VARCHAR)
    private String username;
}
