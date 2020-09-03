package com.jiehuihui.common.entity.search;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Sourecords)搜索历史记录实体类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_sourecords")
public class SouRecords extends Model<SouRecords> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 4046648837669056131L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 搜索类型
    */
        
    private String soukeyword;

    /**
     * 用户关联id
     */
    private String userid;

    /**
    * 排序
    */
        
    private Integer sortnum;

    /**
    * 唯一id
    */
        
    private String ssid;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date createtime;

}