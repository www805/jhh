package com.jiehuihui.web.req;

import com.jiehuihui.common.utils.Page;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class GetMiandanPageParam extends Page {

    private String provinceid; //省
    private String cityid; //市
    private String areaid; //区
    private String keyword; //关键字

    private Integer topnum = 0; //置顶

    private Integer state;
}
