package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Blinddate;
import com.jiehuihui.admin.req.GetBlinddatePageParam;
import java.util.List;
import lombok.Data;

/**
 * (Blinddate)表返回类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */

@Data
public class GetBlinddateVO {

    private GetBlinddatePageParam pageparam;
    private List<Blinddate> pagelist;

}