package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Card)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */

@Data
public class AddUpdateCardParam {

    
    
    @NotBlank(message = "充值卡号码不能为空", groups = {Update.class})
    private String cardnum; //充值卡号码 
    @Min(value = 0,message = "充值卡类型不能为空", groups = {Create.class, Update.class})
    private Integer cardtype; //1月卡2季卡3年卡
    @Min(value = 0,message = "充值卡种类不能为空", groups = {Create.class, Update.class})
    private Integer cardsupertype; //1vip商家2vip会员3管理员

    @Min(value = 1,message = "充值卡数量必须大于1", groups = {Create.class})
    private Integer cardsize; //充值卡数量

    private String cardprefix; //充值卡前缀（选填）

    @Min(value = 0,message = "状态 -1删除 0禁用 1未使用 2已被使用不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态 -1删除 0禁用 1未使用 2已被使用 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id


}