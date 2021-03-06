package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * (Blinddate)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */

@Data
public class AddUpdateBlinddateParam {


    @NotBlank(message = "名字不能为空", groups = {Create.class, Update.class}) 
    private String username; //名字
    @NotBlank(message = "手机号码不能为空", groups = {Create.class, Update.class}) 
    private String phone; //手机号码 
    private String wxnum; //微信号码

    @NotBlank(message = "红娘名字不能为空", groups = {Create.class, Update.class})
    private String matchmake; //关联红娘名字
    @NotBlank(message = "相亲帖id不能为空", groups = {Create.class, Update.class})
    private String blinddateid; //相亲帖关联id
    private String matchmakeruserid;//红娘id

    @NotBlank(message = "备注不能为空", groups = {Create.class, Update.class})
    private String xqdescribe; //备注
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class})
    private Integer sortnum; //排序
    @Min(value = 0,message = "状态不能为空", groups = {Create.class, Update.class})
    private Integer state; //状态 0禁用 1正常 2删除 3已处理 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 

    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区

}