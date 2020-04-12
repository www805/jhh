package com.jiehuihui.admin.req.search;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Soutype)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */

@Data
public class DeleteSoutypeParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}