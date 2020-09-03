package com.jiehuihui.web.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GetSignParam extends Page {

    @NotBlank(message = "用户不能为空")
    private String userid;


}
