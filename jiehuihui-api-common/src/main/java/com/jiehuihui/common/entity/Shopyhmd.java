package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_shopyhmd")
public class Shopyhmd implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 8172608941178991162L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID

    private String shopid; //店铺关联id
    private String mdtitle; //免单标题
    private String mdtag; //免单标签
    private String mddescribe; //标题描述
    private String content; //任务描述
    private String fmimglist; //促销图片集合
    private String mdcontent; //任务描述
    private Integer topnum; // 0正常 1置顶
    private Integer state; //状态0禁用 1正常 2删除
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topendtime; //置顶到期时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;//创建时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime;//活动开始时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;//活动结束时间

}
