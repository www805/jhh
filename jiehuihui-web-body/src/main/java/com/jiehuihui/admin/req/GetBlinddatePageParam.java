package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * (Blinddate)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */

@Data
public class GetBlinddatePageParam extends Page {

    private String username; //名字
    private String phone; //手机号码
    private String wxnum; //微信号码
    private Integer state; //状态

    private List<String> cityList;//城市地区id

}