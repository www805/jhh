package com.jiehuihui.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.utils.Page;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * (Friends)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */

@Data
public class GetFriendsPageParam extends Page {

    private String username; //用户名字
    private String typeid; //类型关联id
    private String content; //朋友圈文字内容

    private List<String> cityList;//城市地区id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topendtime; //置顶到期时间 
    private Integer state; //状态0禁用 1正常2删除

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime; //活动开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime; //活动结束时间

}