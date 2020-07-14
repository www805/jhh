package com.jiehuihui.admin.service.impl.search;

import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.entity.search.Soutype;
import com.jiehuihui.admin.mapper.search.SoutypeMapper;
import com.jiehuihui.admin.service.search.SoutypeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.search.AddUpdateSoutypeParam;
import com.jiehuihui.admin.req.search.DeleteSoutypeParam;
import com.jiehuihui.admin.req.search.GetSoutypePageParam;
import com.jiehuihui.admin.vo.search.GetSoutypeVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Soutype)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */
@Service("soutypeService")
public class SoutypeServiceImpl implements SoutypeService {
    @Resource
    private SoutypeMapper soutypeMapper;

    @Override
    public RResult getSoutype(RResult result) {
        UpdateWrapper<Soutype> ew = new UpdateWrapper<>();
        ew.orderByDesc("sortnum");
        List<Soutype> soutypes = soutypeMapper.selectList(ew);
        result.changeToTrue(soutypes);
        return result;
    }

    @Override
    public RResult getSoutypeByssid(RResult result, DeleteSoutypeParam param) {
        UpdateWrapper<Soutype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Soutype> soutypes = soutypeMapper.selectList(ew);
        if (null != soutypes && soutypes.size() > 0) {
            Soutype soutype = soutypes.get(0);
            result.changeToTrue(soutype);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getSoutypePage(RResult result, GetSoutypePageParam param) {

        GetSoutypeVO soutypeVO = new GetSoutypeVO();

        UpdateWrapper<Soutype> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getTypename())){
            ew.like("typename", param.getTypename());
        }

        ew.orderByDesc("sortnum");

        Integer count = soutypeMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Soutype> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Soutype> sqCacheList = soutypeMapper.selectPage(page, ew);

        soutypeVO.setPagelist(sqCacheList.getRecords());
        soutypeVO.setPageparam(param);

        result.changeToTrue(soutypeVO);
        return result;
    }

    @Override
    public RResult addSoutype(RResult result, AddUpdateSoutypeParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Soutype> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != soutypeMapper.selectList(ew) && soutypeMapper.selectList(ew).size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Soutype> ew = new UpdateWrapper<>();
        ew.eq("typename", param.getTypename());
        List<Soutype> TypeList = soutypeMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Soutype soutype = new Soutype();
        soutype.setTypename(param.getTypename());
        soutype.setSortnum(param.getSortnum());
        soutype.setSsid(ssid);
        soutype.setState(param.getState());
        int insert = soutypeMapper.insert(soutype);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateSoutype(RResult result, AddUpdateSoutypeParam param) {
        //先校验是否已经存在
        UpdateWrapper<Soutype> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("typename", param.getTypename());
        ewcheck.ne("ssid", param.getSsid());
        List<Soutype> soutypes = soutypeMapper.selectList(ewcheck);
        if (null != soutypes && soutypes.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Soutype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Soutype soutype = new Soutype();
        soutype.setTypename(param.getTypename());
        soutype.setSortnum(param.getSortnum());
        soutype.setState(param.getState());
        soutype.setState(param.getState());

        int update = soutypeMapper.update(soutype, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getTypename());

        return result;
    }

    @Override
    public RResult deleteSoutype(RResult result, DeleteSoutypeParam param) {
        UpdateWrapper<Soutype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Soutype soutype = soutypeMapper.selectOne(ew);
        if(null == soutype){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = soutypeMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + soutype.getTypename());
        return result;
    }
}