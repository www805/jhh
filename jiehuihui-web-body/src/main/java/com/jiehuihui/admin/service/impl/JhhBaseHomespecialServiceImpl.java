package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.dao.JhhBaseHomespecialDao;
import com.jiehuihui.admin.entity.JhhBaseHomespecial;
import com.jiehuihui.admin.service.JhhBaseHomespecialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JhhBaseHomespecial)表服务实现类
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
@Service("jhhBaseHomespecialService")
public class JhhBaseHomespecialServiceImpl implements JhhBaseHomespecialService {
    @Resource
    private JhhBaseHomespecialDao jhhBaseHomespecialDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JhhBaseHomespecial queryById(Long id) {
        return this.jhhBaseHomespecialDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JhhBaseHomespecial> queryAllByLimit(int offset, int limit) {
        return this.jhhBaseHomespecialDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jhhBaseHomespecial 实例对象
     * @return 实例对象
     */
    @Override
    public JhhBaseHomespecial insert(JhhBaseHomespecial jhhBaseHomespecial) {
        this.jhhBaseHomespecialDao.insert(jhhBaseHomespecial);
        return jhhBaseHomespecial;
    }

    /**
     * 修改数据
     *
     * @param jhhBaseHomespecial 实例对象
     * @return 实例对象
     */
    @Override
    public JhhBaseHomespecial update(JhhBaseHomespecial jhhBaseHomespecial) {
        this.jhhBaseHomespecialDao.update(jhhBaseHomespecial);
        return this.queryById(jhhBaseHomespecial.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.jhhBaseHomespecialDao.deleteById(id) > 0;
    }
}