package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.entity.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_user")
public class User implements Serializable,AuthCachePrincipal {

    @TableField(exist = false)
    private static final long serialVersionUID = -6808714847070286018L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID
    private String userlogin; //登录名
    private String username; //用户名
    private String myname; //真实名字
    private String password; //密码
    private String tximg; //头像图片地址
    private String phone; //手机号码
    @TableField(exist = false)
    private List<Role> roleList; //角色集合
    private String rolelistid; //角色集合
    private Long sign; //积分
    private Integer sex; //性别
    private Integer state; //状态
    private String ssid; //唯一id
    private String yhjid; //优惠关联id
    private String cityzhongid; //城市中间表关联id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastlogintime; //最后登录时间


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date vipstatetime; //VIP开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date vipendtime; //VIP到期时间

    @TableField(exist = false)
    private ProvinceCityArea city; //城市关联实体类


    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
