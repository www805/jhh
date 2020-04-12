package com.jiehuihui.admin.vo.search;

import com.jiehuihui.admin.req.search.GetHotsouPageParam;
import com.jiehuihui.common.entity.search.Hotsou;
import java.util.List;
import lombok.Data;

/**
 * (Hotsou)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */


@Data
public class GetHotsouVO {

    private GetHotsouPageParam pageparam;
    private List<Hotsou> pagelist;

}