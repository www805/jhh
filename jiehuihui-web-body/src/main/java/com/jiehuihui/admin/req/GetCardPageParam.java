package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Card)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */

@Data
public class GetCardPageParam extends Page {

    private String cardnum; //充值卡号码
    private Integer cardtype; //1月卡2季卡3年卡
    private String username; //使用者名
    private String topup; //充值人名
    private String parentname; //生成者名

    private String employtime; //使用时间

}