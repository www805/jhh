package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_images")
public class Images implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -5687456553848342308L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID
    private String imgurl; //图片访问地址
    private String pathurl; //图片存储路径
    private String md; //图片md5
    private String imgname; //图片名称
    private Long imgsize; //图片大小
    private String ssid; //唯一id


}
