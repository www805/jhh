package com.jiehuihui.admin.service.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeSlideshowParam;
import com.jiehuihui.admin.req.home.DeleteHomeSlideshowParam;
import com.jiehuihui.admin.req.home.GetHomeSlideshowPageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;

public interface HomeSlideshowService {

    //获取首页分类
    RResult getHomeSlideshow(RResult result, GetHomeWebParam param);

    //获取一条首页分类
    RResult getHomeSlideshowByssid(RResult result, DeleteHomeSlideshowParam param);

    //获取首页分类，分页
    RResult getHomeSlideshowPage(RResult result, GetHomeSlideshowPageParam param);

    //添加首页分类
    RResult addHomeSlideshow(RResult result, AddUpdateHomeSlideshowParam param);

    //修改首页分类
    RResult updateHomeSlideshow(RResult result, AddUpdateHomeSlideshowParam param);

    //删除首页分类
    RResult deleteHomeSlideshow(RResult result, DeleteHomeSlideshowParam param);


}
