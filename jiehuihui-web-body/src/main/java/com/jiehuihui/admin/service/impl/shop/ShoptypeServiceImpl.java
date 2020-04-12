package com.jiehuihui.admin.service.impl.shop;

import com.jiehuihui.common.entity.shop.Shoptype;
import com.jiehuihui.admin.mapper.shop.ShoptypeMapper;
import com.jiehuihui.admin.service.shop.ShoptypeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.shop.AddUpdateShoptypeParam;
import com.jiehuihui.admin.req.shop.DeleteShoptypeParam;
import com.jiehuihui.admin.req.shop.GetShoptypePageParam;
import com.jiehuihui.admin.vo.shop.GetShoptypeVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Shoptype)店铺类型服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */
@Service("shoptypeService")
public class ShoptypeServiceImpl implements ShoptypeService {
    @Resource
    private ShoptypeMapper shoptypeMapper;

    @Override
    public RResult getShoptype(RResult result) {
        UpdateWrapper<Shoptype> ew = new UpdateWrapper<>();
        ew.orderByDesc("sortnum");
        List<Shoptype> shoptypes = shoptypeMapper.selectList(ew);
        result.changeToTrue(shoptypes);
        return result;
    }

    @Override
    public RResult getShoptypeByssid(RResult result, DeleteShoptypeParam param) {
        UpdateWrapper<Shoptype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        Shoptype shoptype = shoptypeMapper.selectList(ew).get(0);
        if (null != shoptype) {
            result.changeToTrue(shoptype);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getShoptypePage(RResult result, GetShoptypePageParam param) {

        GetShoptypeVO shoptypeVO = new GetShoptypeVO();

        UpdateWrapper<Shoptype> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getTypename())){
            ew.like("typename", param.getTypename());
        }

        ew.orderByDesc("sortnum");

        Integer count = shoptypeMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Shoptype> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shoptype> sqCacheList = shoptypeMapper.selectPage(page, ew);

        shoptypeVO.setPagelist(sqCacheList.getRecords());
        shoptypeVO.setPageparam(param);

        result.changeToTrue(shoptypeVO);
        return result;
    }

    @Override
    public RResult addShoptype(RResult result, AddUpdateShoptypeParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Shoptype> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != shoptypeMapper.selectList(ew)){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Shoptype> ew = new UpdateWrapper<>();
        ew.eq("typename", param.getTypename());
        List<Shoptype> TypeList = shoptypeMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Shoptype shoptype = new Shoptype();
        shoptype.setTypename(param.getTypename());
        shoptype.setSortnum(param.getSortnum());
        shoptype.setSsid(ssid);
        int insert = shoptypeMapper.insert(shoptype);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateShoptype(RResult result, AddUpdateShoptypeParam param) {
        //先校验是否已经存在
        UpdateWrapper<Shoptype> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("typename", param.getTypename());
        ewcheck.ne("ssid", param.getSsid());
        List<Shoptype> shoptypes = shoptypeMapper.selectList(ewcheck);
        if (null != shoptypes && shoptypes.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Shoptype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shoptype shoptype = new Shoptype();
        shoptype.setTypename(param.getTypename());
        shoptype.setSortnum(param.getSortnum());
        int update = shoptypeMapper.update(shoptype, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getTypename());

        return result;
    }

    @Override
    public RResult deleteShoptype(RResult result, DeleteShoptypeParam param) {
        UpdateWrapper<Shoptype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shoptype shoptype = shoptypeMapper.selectOne(ew);
        if(null == shoptype){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = shoptypeMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + shoptype.getTypename());
        return result;
    }
}