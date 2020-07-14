package com.jiehuihui.admin.mapper.home;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.Homespecial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (JhhBaseHomespecial)特价表数据库访问层
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
public interface HomespecialMapper extends BaseMapper<Homespecial> {

    IPage<Homespecial> getHomespecialPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectHomespecialCount(@Param("ew") Wrapper wrapper);

}