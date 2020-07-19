package com.jiehuihui.admin.service.impl.shop;

import com.jiehuihui.common.entity.search.Soutype;
import com.jiehuihui.common.entity.shop.Shopcolor;
import com.jiehuihui.admin.mapper.shop.ShopcolorMapper;
import com.jiehuihui.admin.service.shop.ShopcolorService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.shop.AddUpdateShopcolorParam;
import com.jiehuihui.admin.req.shop.DeleteShopcolorParam;
import com.jiehuihui.admin.req.shop.GetShopcolorPageParam;
import com.jiehuihui.admin.vo.shop.GetShopcolorVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Shopcolor)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */
@Service("shopcolorService")
public class ShopcolorServiceImpl implements ShopcolorService {
    @Resource
    private ShopcolorMapper shopcolorMapper;

    @Override
    public RResult getShopcolor(RResult result, GetShopcolorPageParam param) {
        UpdateWrapper<Shopcolor> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        if(null != param.getColortype() && param.getColortype() >= 0){
            ew.eq("colortype", param.getColortype());
        }
        ew.orderByDesc("sortnum");
        List<Shopcolor> shopcolors = shopcolorMapper.selectList(ew);
        result.changeToTrue(shopcolors);
        return result;
    }

    @Override
    public RResult getShopcolorByssid(RResult result, DeleteShopcolorParam param) {
        UpdateWrapper<Shopcolor> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Shopcolor> soutypes = shopcolorMapper.selectList(ew);
        if (null != soutypes && soutypes.size() > 0) {
            Shopcolor shopcolor = soutypes.get(0);
            result.changeToTrue(shopcolor);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getShopcolorPage(RResult result, GetShopcolorPageParam param) {

        GetShopcolorVO shopcolorVO = new GetShopcolorVO();

        UpdateWrapper<Shopcolor> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getColor())){
            ew.like("color", param.getColor());
        }
        if(StringUtils.isNotEmpty(param.getColorname())){
            ew.like("colorname", param.getColorname());
        }
        if(null != param.getColortype() && param.getColortype() >= 0){
            ew.eq("colortype", param.getColortype());
        }

        ew.orderByDesc("sortnum");

        Integer count = shopcolorMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Shopcolor> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shopcolor> sqCacheList = shopcolorMapper.selectPage(page, ew);

        shopcolorVO.setPagelist(sqCacheList.getRecords());
        shopcolorVO.setPageparam(param);

        result.changeToTrue(shopcolorVO);
        return result;
    }

    @Override
    public RResult addShopcolor(RResult result, AddUpdateShopcolorParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Shopcolor> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Shopcolor> shopcolors = shopcolorMapper.selectList(ew);
            if(null != shopcolors && shopcolors.size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Shopcolor> ew = new UpdateWrapper<>();
        if(StringUtils.isNoneBlank(param.getColor())){
            ew.eq("color", param.getColor());
        }
        if(StringUtils.isNoneBlank(param.getColorname())){
            ew.eq("colorname", param.getColorname());
        }

        List<Shopcolor> TypeList = shopcolorMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Shopcolor shopcolor = new Shopcolor();
        shopcolor.setColor(param.getColor());
        shopcolor.setColorname(param.getColorname());
        shopcolor.setSortnum(param.getSortnum());
        shopcolor.setSsid(ssid);
        shopcolor.setColortype(param.getColortype());
        shopcolor.setState(param.getState());
        int insert = shopcolorMapper.insert(shopcolor);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateShopcolor(RResult result, AddUpdateShopcolorParam param) {
        //先校验是否已经存在
        UpdateWrapper<Shopcolor> ewcheck = new UpdateWrapper<>();
        if(StringUtils.isNoneBlank(param.getColor())){
            ewcheck.eq("color", param.getColor());
        }
        if(StringUtils.isNoneBlank(param.getColorname())){
            ewcheck.eq("colorname", param.getColorname());
        }
        ewcheck.ne("ssid", param.getSsid());
        List<Shopcolor> shopcolors = shopcolorMapper.selectList(ewcheck);
        if (null != shopcolors && shopcolors.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Shopcolor> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopcolor shopcolor = new Shopcolor();
        shopcolor.setColor(param.getColor());
        shopcolor.setColorname(param.getColorname());
        shopcolor.setColortype(param.getColortype());
        shopcolor.setSortnum(param.getSortnum());
        shopcolor.setState(param.getState());

        int update = shopcolorMapper.update(shopcolor, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getColorname());

        return result;
    }

    @Override
    public RResult deleteShopcolor(RResult result, DeleteShopcolorParam param) {
        UpdateWrapper<Shopcolor> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopcolor shopcolor = shopcolorMapper.selectOne(ew);
        if(null == shopcolor){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = shopcolorMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + shopcolor.getColorname());
        return result;
    }
}