package com.jiehuihui.admin.req.search;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Hotsou)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */

@Data
public class DeleteHotsouParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}