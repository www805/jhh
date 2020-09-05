package com.jiehuihui.admin.mapper;

import com.jiehuihui.common.entity.Blinddateinfo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Blinddateinfo)表数据库访问层
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */
public interface BlinddateinfoMapper extends BaseMapper<Blinddateinfo> {

    IPage<Blinddateinfo> getBlinddateinfoPage(Page<?> page, @Param("ew") Wrapper wrapper);

    List<Blinddateinfo> selectBlinddateinfoList(@Param("ew") Wrapper wrapper);

    int selectBlinddateinfoCount(@Param("ew") Wrapper wrapper);

}