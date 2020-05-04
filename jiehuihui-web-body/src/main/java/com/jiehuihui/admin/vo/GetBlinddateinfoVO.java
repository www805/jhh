package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Blinddateinfo;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import java.util.List;
import lombok.Data;

/**
 * (Blinddateinfo)表返回类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */

@Data
public class GetBlinddateinfoVO {

    private GetBlinddateinfoPageParam pageparam;
    private List<Blinddateinfo> pagelist;

}