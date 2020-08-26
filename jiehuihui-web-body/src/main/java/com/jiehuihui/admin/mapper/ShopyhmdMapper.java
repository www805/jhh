package com.jiehuihui.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.Shopyhmd;
import com.jiehuihui.common.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 免单关联表
 */
public interface ShopyhmdMapper extends BaseMapper<Shopyhmd> {

    IPage<Shopyhmd> selectShopyhmdPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectShopyhmdCount(@Param("ew") Wrapper wrapper);

}
