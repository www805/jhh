package com.jiehuihui.admin.service.home;

import com.jiehuihui.admin.req.AddUpdateSpecialParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.common.utils.RResult;

import java.util.List;

/**
 * (JhhBaseHomespecial)特价表服务接口
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
public interface HomespecialService {

    /**
     * 通过ID查询单条数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult getSpecialByssid(RResult result, DeleteSpecialParam param);

    /**
     * 查询多条数据
     * @param param  实例参数对象
     * @return 返回对象
     */
    RResult getSpecialPage(RResult result, GetSpecialPageParam param);

    /**
     * 新增数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult addSpecial(RResult result, AddUpdateSpecialParam param);

    /**
     * 修改数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult updateSpecial(RResult result, AddUpdateSpecialParam param);

    /**
     * 通过主键删除数据
     *
     * @param param 实例参数对象
     * @return 是否成功返回对象
     */
    RResult deleteSpecial(RResult result, DeleteSpecialParam param);

}