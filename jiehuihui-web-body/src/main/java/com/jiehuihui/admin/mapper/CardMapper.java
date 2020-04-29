package com.jiehuihui.admin.mapper;

import com.jiehuihui.admin.entity.Card;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * (Card)表数据库访问层
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */
public interface CardMapper extends BaseMapper<Card> {

    IPage<Card> getCardPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectCardCount(@Param("ew") Wrapper wrapper);

}