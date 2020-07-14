package com.jiehuihui.admin.mapper.shop;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.shop.Shopinfo;
import org.apache.ibatis.annotations.Param;

/**
 * (JhhShopinfo)店铺表数据库访问层
 *
 * @author zhuang
 * @since 2020-07-12 16:40:14
 */
public interface ShopinfoMapper extends BaseMapper<Shopinfo> {

    IPage<Shopinfo> getShopinfoPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectShopinfoCount(@Param("ew") Wrapper wrapper);

}