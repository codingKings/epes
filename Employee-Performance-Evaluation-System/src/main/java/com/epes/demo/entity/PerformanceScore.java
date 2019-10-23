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

    /**
     * 进度评分 0：提前 1：正常 2：搁置
     */
    @Column(name = "progressScore", type = MySqlTypeConstant.VARCHAR)
    private String progressScore;

    /**
     *  质量评分 0：好 1：中 2：差
     */
    @Column(name = "qualityScore", type = MySqlTypeConstant.VARCHAR)
    private String qualityScore;

    /**
     * 专业素质 0：优 1：良 2：中 3：差
     */
    @Column(name = "professionScore", type = MySqlTypeConstant.VARCHAR)
    private String professionScore;


    /**
     * 敬业精神 0：优 1：良 2：中 3：差
     */
    @Column(name = "dedicationScore", type = MySqlTypeConstant.VARCHAR)
    private String dedicationScore;

    /**
     * 主动担当 0：优 1：良 2：中 3：差
     */
    @Column(name = "dutyScore", type = MySqlTypeConstant.VARCHAR)
    private String dutyScore;

    /**
     * 工作成交 0：优 1：良 2：中 3：差
     */
    @Column(name = "businessScore", type = MySqlTypeConstant.VARCHAR)
    private String businessScore;

    /**
     * 创新能力 0：优 1：良 2：中 3：差
     */
    @Column(name = "innovationScore", type = MySqlTypeConstant.VARCHAR)
    private String innovationScore;

    /**
     * 团结协作 0：优 1：良 2：中 3：差
     */
    @Column(name = "teamScore", type = MySqlTypeConstant.VARCHAR)
    private String teamScore;

    /**
     * 类型：0为任务进度评价；1为综合评价;
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
     * 关联任务
     */
    @Column(name = "pojid", type = MySqlTypeConstant.VARCHAR)
    private String pojid;

    /**
     * 关联评分人
     */
    @Column(name = "scoreUserid", type = MySqlTypeConstant.VARCHAR, isNull = false)
    private String scoreUserid;

    /**
     * 关联被评分人
     */
    @Column(name = "userid", type = MySqlTypeConstant.VARCHAR, isNull = false)
    private String userid;

    @Column(name = "username", type = MySqlTypeConstant.VARCHAR)
    private String username;

    /**
     * 评价月份
     */
    @Column(name = "scoreDate", type = MySqlTypeConstant.VARCHAR,length = 9,isNull = false)
    private String scoreDate;


}
