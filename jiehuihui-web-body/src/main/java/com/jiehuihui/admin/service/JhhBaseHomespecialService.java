package com.jiehuihui.admin.service;

import com.jiehuihui.admin.entity.JhhBaseHomespecial;

import java.util.List;

/**
 * (JhhBaseHomespecial)表服务接口
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
public interface JhhBaseHomespecialService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JhhBaseHomespecial queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JhhBaseHomespecial> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jhhBaseHomespecial 实例对象
     * @return 实例对象
     */
    JhhBaseHomespecial insert(JhhBaseHomespecial jhhBaseHomespecial);

    /**
     * 修改数据
     *
     * @param jhhBaseHomespecial 实例对象
     * @return 实例对象
     */
    JhhBaseHomespecial update(JhhBaseHomespecial jhhBaseHomespecial);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}