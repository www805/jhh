package com.jiehuihui.common.entity.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省份城市区域基础表
 * 以区域表为底层表，让市表，省表继承，拓展
 *  该类用作返回值
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AreaVO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -6571647304060972124L;


    private String id = IdWorker.getId() + "";

    /**
     * 父级区域
     */
    private String cityregionsuper="0";
    /**
     * 城市区域名称
     */
    private String cityregionname;
    /**
     * 类型
     */
    private Integer cityregiontype;
    /**
     * 排序
     */
    private Integer sortnum;
    /**
     * 状态
     */
    private Integer state;

    /**
     * 下级城市
     */
}
