package com.jiehuihui.web.vo;

import com.jiehuihui.common.entity.Sign;
import com.jiehuihui.web.req.GetSignParam;
import lombok.Data;

import java.util.List;

/**
 * (签到)表返回类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */

@Data
public class GetSignVO {

    private Long jifen;
    private GetSignParam pageparam;
    private List<Sign> pagelist;

}