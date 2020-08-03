package com.jiehuihui.admin.service.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeTypeParam;
import com.jiehuihui.admin.req.home.DeleteHomeTypeParam;
import com.jiehuihui.admin.req.home.GetHomeTypePageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;

/**
 * (HomeType)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 20:58:44
 */
public interface HomeTypeService {

    //获取首页分类
    RResult getHomeType(RResult result, GetHomeWebParam param);

    //获取一条首页分类
    RResult getHomeTypeByssid(RResult result, DeleteHomeTypeParam param);

    //获取首页分类，分页
    RResult getHomeTypePage(RResult result, GetHomeTypePageParam param);

    //添加首页分类
    RResult addHomeType(RResult result, AddUpdateHomeTypeParam param);

    //修改首页分类
    RResult updateHomeType(RResult result, AddUpdateHomeTypeParam param);

    //删除首页分类
    RResult deleteHomeType(RResult result, DeleteHomeTypeParam param);

}