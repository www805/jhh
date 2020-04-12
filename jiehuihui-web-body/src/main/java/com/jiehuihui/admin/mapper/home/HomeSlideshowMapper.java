package com.jiehuihui.admin.mapper.home;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import org.apache.ibatis.annotations.Param;

/**
 * 首页轮播图
 */
public interface HomeSlideshowMapper extends BaseMapper<HomeSlideshow> {

    IPage<HomeSlideshow> getHomeSlideshowPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectHomeSlideshowCount(@Param("ew") Wrapper wrapper);

}
