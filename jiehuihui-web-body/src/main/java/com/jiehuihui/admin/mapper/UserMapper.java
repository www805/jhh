package com.jiehuihui.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> getUserPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectUserCount(@Param("ew") Wrapper wrapper);

    void addjifen(@Param("userid") String userid, @Param("num") int num);

    Long getjifen(@Param("userid") String userid);

    List<User> getUserList(@Param("ew") Wrapper wrapper);
}