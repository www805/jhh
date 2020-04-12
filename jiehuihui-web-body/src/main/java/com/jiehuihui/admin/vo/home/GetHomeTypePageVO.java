package com.jiehuihui.admin.vo.home;

import com.jiehuihui.admin.req.home.GetHomeTypePageParam;
import com.jiehuihui.common.entity.home.HomeType;
import lombok.Data;

import java.util.List;

@Data
public class GetHomeTypePageVO {

    private GetHomeTypePageParam pageparam;
    private List<HomeType> pagelist;

}
