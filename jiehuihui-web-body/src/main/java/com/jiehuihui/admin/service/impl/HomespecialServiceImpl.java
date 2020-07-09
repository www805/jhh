package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.HomespecialMapper;
import com.jiehuihui.admin.req.AddUpdateSpecialParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.common.entity.Friendstype;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.admin.service.HomespecialService;
import com.jiehuihui.common.utils.RResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JhhBaseHomespecial)表服务实现类
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
@Service("homespecialService")
public class HomespecialServiceImpl implements HomespecialService {
    @Resource
    private HomespecialMapper homespecialMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param param 主键
     * @return 实例对象
     */

    @Override
    public RResult getSpecialByssid(RResult<Homespecial> result, DeleteSpecialParam param) {
        UpdateWrapper<Homespecial> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        Homespecial homespecial = homespecialMapper.selectList(ew).get(0);
        if (null != homespecial) {
            result.changeToTrue(homespecial);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    /**
     * 查询多条数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult getSpecialPage(RResult<Homespecial> result, GetSpecialPageParam param) {
        return null;
    }

    /**
     * 新增数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult addSpecial(RResult<Homespecial> result, AddUpdateSpecialParam param) {
        return null;
    }

    /**
     * 修改数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult updateSpecial(RResult<Homespecial> result, AddUpdateSpecialParam param) {
        return null;
    }

    /**
     * 通过条件删除数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult deleteSpecial(RResult<Homespecial> result, DeleteSpecialParam param) {
        return null;
    }
}