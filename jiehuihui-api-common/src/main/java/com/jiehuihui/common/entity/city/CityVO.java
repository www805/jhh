package com.jiehuihui.common.entity.city;


import com.baomidou.mybatisplus.annotation.TableField;
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
public class CityVO extends AreaVO {

    /**
     * 下级区域表
     */
    @TableField(exist = false)
    private List<AreaVO> children;


}
