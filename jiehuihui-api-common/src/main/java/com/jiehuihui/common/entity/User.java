package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Date;

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
    private Integer jifen; //积分
    private Integer state; //状态
    private String ssid; //唯一id
    private String city; //城市信息
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastlogintime; //最后登录时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endtime; //VIP到期时间

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
