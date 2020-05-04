package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Blinddate)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */

@Data
public class DeleteBlinddateParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}