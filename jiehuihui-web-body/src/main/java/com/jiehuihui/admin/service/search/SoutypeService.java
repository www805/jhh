package com.jiehuihui.admin.service.search;

import com.jiehuihui.admin.req.search.AddUpdateSoutypeParam;
import com.jiehuihui.admin.req.search.DeleteSoutypeParam;
import com.jiehuihui.admin.req.search.GetSoutypePageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Soutype)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */
public interface SoutypeService {

    //获取搜索类型
    RResult getSoutype(RResult result);

    //获取一条搜索类型
    RResult getSoutypeByssid(RResult result, DeleteSoutypeParam param);

    //获取搜索类型，分页
    RResult getSoutypePage(RResult result, GetSoutypePageParam param);

    //添加搜索类型
    RResult addSoutype(RResult result, AddUpdateSoutypeParam param);

    //修改搜索类型
    RResult updateSoutype(RResult result, AddUpdateSoutypeParam param);

    //删除搜索类型
    RResult deleteSoutype(RResult result, DeleteSoutypeParam param);

}