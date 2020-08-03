package com.jiehuihui.admin.service.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeGgParam;
import com.jiehuihui.admin.req.home.DeleteHomeGgParam;
import com.jiehuihui.admin.req.home.GetHomeggPageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;

public interface HomeGgService {

    //获取首页公告
    RResult getHomegg(RResult result, GetHomeWebParam param);

    //获取一条首页公告
    RResult getHomeggByssid(RResult result, DeleteHomeGgParam param);

    //获取首页公告，分页
    RResult getHomeggPage(RResult result, GetHomeggPageParam param);

    //添加首页公告
    RResult addHomegg(RResult result, AddUpdateHomeGgParam param);

    //修改首页公告
    RResult updateHomegg(RResult result, AddUpdateHomeGgParam param);

    //删除首页公告
    RResult deleteHomegg(RResult result, DeleteHomeGgParam param);


}
