package com.jiehuihui.web.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetSearchParam extends Page {

    @NotBlank(message = "类型不能为空")
    private String typeid;
    private String userid;
    private String keyword;


    private String shoptype;//商品类型
    private String sortnum;//排序
    private List<String> cityList;//城市地区id

    private String provinceid; //省
    private String cityid; //市
    private String areaid; //区

    private Integer pageSize;//显示条数
    private Integer currPage;//当前页

}
