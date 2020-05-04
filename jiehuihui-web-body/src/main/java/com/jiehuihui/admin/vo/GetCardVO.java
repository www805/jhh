package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Card;
import com.jiehuihui.admin.req.GetCardPageParam;
import java.util.List;
import lombok.Data;

/**
 * (Card)表返回类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */

@Data
public class GetCardVO {

    private GetCardPageParam pageparam;
    private List<Card> pagelist;

}