package com.jiehuihui.web.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PutXiangqinParam {

    @NotBlank(message = "姓名不能为空", groups = {Create.class, Update.class})
    private String username;//姓名
    @NotBlank(message = "电话不能为空", groups = {Create.class, Update.class})
    private String iphone;//电话
    private String wxnum;//微信
    private String xqdescribe;//留言
    @NotBlank(message = "红娘不能为空", groups = {Create.class, Update.class})
    private String matchmakeruserid;//红娘id
    @NotBlank(message = "相亲帖id不能为空", groups = {Create.class, Update.class})
    private String blinddateid; //相亲帖关联id

    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区

}
