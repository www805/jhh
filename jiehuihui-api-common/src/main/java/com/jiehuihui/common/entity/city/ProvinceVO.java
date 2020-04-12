package com.jiehuihui.common.entity.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 该类用作返回值
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_province")//省份表
public class ProvinceVO extends AreaVO {

    /**
     * 下级城市表
     */
    @TableField(exist = false)
    private List<CityVO> children;
}
