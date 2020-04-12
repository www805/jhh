package com.jiehuihui.common.entity.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_cityzhong")//省份城市区域中间表
public class Cityzhong implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -7902286359513681822L;

    private Long id;
    private String provinceid;
    private String cityid;
    private String areaid;

}
