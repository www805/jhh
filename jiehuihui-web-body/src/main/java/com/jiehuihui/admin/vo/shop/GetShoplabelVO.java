package com.jiehuihui.admin.vo.shop;

import com.jiehuihui.admin.req.shop.GetShoplabelPageParam;
import com.jiehuihui.common.entity.shop.Shoplabel;
import java.util.List;

/**
 * (Shoplabel)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */
import lombok.Data;

@Data
public class GetShoplabelVO {

    private GetShoplabelPageParam pageparam;
    private List<Shoplabel> pagelist;

}