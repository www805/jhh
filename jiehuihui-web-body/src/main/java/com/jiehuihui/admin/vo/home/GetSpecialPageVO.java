package com.jiehuihui.admin.vo.home;

import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.common.entity.Homespecial;
import lombok.Data;

import java.util.List;

@Data
public class GetSpecialPageVO {

    private GetSpecialPageParam pageparam;
    private List<Homespecial> pagelist;

}
