package com.jiehuihui.admin.req.shop;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * (Shoplabel)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */

@Data
public class GetShoplabelPageParam extends Page {

    @NotBlank(message = "标签文字不能为空")
    private String labelname; //标签文字 

    @NotBlank(message = "城市ssid不能为空")
    private String cityid; //城市ssid

}