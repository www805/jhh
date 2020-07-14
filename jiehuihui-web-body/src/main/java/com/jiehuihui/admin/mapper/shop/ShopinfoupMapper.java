package com.jiehuihui.admin.mapper.shop;

import com.jiehuihui.common.entity.Shopinfoup;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * (Shopinfoup)表数据库访问层
 *
 * @author zhuang
 * @since 2020-05-05 20:56:59
 */
public interface ShopinfoupMapper extends BaseMapper<Shopinfoup> {

    IPage<Shopinfoup> getShopinfoupPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectShopinfoupCount(@Param("ew") Wrapper wrapper);

}