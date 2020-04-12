package com.jiehuihui.admin.vo.home;

import com.jiehuihui.admin.req.home.GetHomeSlideshowPageParam;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import lombok.Data;

import java.util.List;

@Data
public class GetHomeSlideshowPageVO {

    private GetHomeSlideshowPageParam pageparam;
    private List<HomeSlideshow> pagelist;

}
