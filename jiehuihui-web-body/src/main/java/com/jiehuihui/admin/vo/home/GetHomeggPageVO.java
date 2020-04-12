package com.jiehuihui.admin.vo.home;

import com.jiehuihui.admin.req.home.GetHomeggPageParam;
import com.jiehuihui.common.entity.home.HomeGg;
import lombok.Data;

import java.util.List;

@Data
public class GetHomeggPageVO {

    private GetHomeggPageParam pageparam;
    private List<HomeGg> pagelist;

}
