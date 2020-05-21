package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.admin.req.GetShopinfoupPageParam;
import java.util.List;
import lombok.Data;

/**
 * (Shopinfoup)表返回类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */

@Data
public class GetShopinfoupVO {

    private GetShopinfoupPageParam pageparam;
    private List<Shopinfoup> pagelist;

}