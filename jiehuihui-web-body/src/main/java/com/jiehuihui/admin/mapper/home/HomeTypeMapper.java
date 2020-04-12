package com.jiehuihui.admin.mapper.home;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.home.HomeType;
import org.apache.ibatis.annotations.Param;

/**
 * 首页类型
 */
public interface HomeTypeMapper extends BaseMapper<HomeType> {

    IPage<HomeType> getHomeTypePage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectHomeTypeCount(@Param("ew") Wrapper wrapper);

}
