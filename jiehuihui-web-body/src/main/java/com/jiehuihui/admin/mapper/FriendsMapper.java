package com.jiehuihui.admin.mapper;

import com.jiehuihui.common.entity.Friends;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Friends)表数据库访问层
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */
public interface FriendsMapper extends BaseMapper<Friends> {

    IPage<Friends> getFriendsPage(Page<?> page, @Param("ew") Wrapper wrapper);

    List<Friends> getFriendsList(@Param("ew") Wrapper wrapper);

    int selectFriendsCount(@Param("ew") Wrapper wrapper);

}