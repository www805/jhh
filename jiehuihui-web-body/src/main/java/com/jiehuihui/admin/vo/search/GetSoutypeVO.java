package com.jiehuihui.admin.vo.search;

import com.jiehuihui.common.entity.search.Soutype;
import com.jiehuihui.admin.req.search.GetSoutypePageParam;
import java.util.List;
import lombok.Data;

/**
 * (Soutype)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */

@Data
public class GetSoutypeVO {

    private GetSoutypePageParam pageparam;
    private List<Soutype> pagelist;

}