package com.jiehuihui.admin.mapper.home;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.home.HomeGg;

import org.apache.ibatis.annotations.Param;

/**
 * 首页公告
 */
public interface HomeGgMapper extends BaseMapper<HomeGg> {

    IPage<HomeGg> getHomeggPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectHomeggCount(@Param("ew") Wrapper wrapper);
}
