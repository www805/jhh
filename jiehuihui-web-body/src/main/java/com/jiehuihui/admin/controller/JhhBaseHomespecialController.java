package com.jiehuihui.admin.controller;

import com.jiehuihui.admin.entity.JhhBaseHomespecial;
import com.jiehuihui.admin.service.JhhBaseHomespecialService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (JhhBaseHomespecial)表控制层
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
@RestController
@RequestMapping("jhhBaseHomespecial")
public class JhhBaseHomespecialController {
    /**
     * 服务对象
     */
    @Resource
    private JhhBaseHomespecialService jhhBaseHomespecialService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JhhBaseHomespecial selectOne(Long id) {
        return this.jhhBaseHomespecialService.queryById(id);
    }

}