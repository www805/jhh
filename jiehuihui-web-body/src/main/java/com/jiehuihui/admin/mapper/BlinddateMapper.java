package com.jiehuihui.admin.mapper;

import com.jiehuihui.common.entity.Blinddate;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * (Blinddate)表数据库访问层
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */
public interface BlinddateMapper extends BaseMapper<Blinddate> {

    IPage<Blinddate> getBlinddatePage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectBlinddateCount(@Param("ew") Wrapper wrapper);

}