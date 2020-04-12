package com.jiehuihui.admin.req.search;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Hotsou)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */

@Data
public class GetHotsouPageParam extends Page {

    
    
    @NotBlank(message = "关键字不能为空") 
    private String keyword; //关键字

    @Min(value = 0,message = "排序不能为空")
    private Integer sortnum; //排序

    @Max(value = 2,message = "状态不能为空")
    private Integer state; //状态 0禁用 1正常 2删除

    @NotBlank(message = "唯一id不能为空") 
    private String ssid; //唯一id 


}