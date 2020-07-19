package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_zhidinng")
public class Zhiding implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -385476286399937340L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID

    private String shopssid; //店铺关联ssid
    private Integer renzheng; //街惠惠认证 0正常 1置顶
    private Integer souzhiding; //搜索置顶 0正常 1置顶
    private Integer mdzhiding; //免单置顶 0正常 1置顶
    private Integer syzhiding; //首页优惠显示0正常 1显示

}
