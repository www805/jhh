package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jiehuihui.common.entity.Card;
import com.jiehuihui.admin.mapper.CardMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.UserToRoleMapper;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.admin.service.CardService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateCardParam;
import com.jiehuihui.admin.req.DeleteCardParam;
import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.admin.vo.GetCardVO;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.Usertorole;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Card)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */
@Service("cardService")
public class CardServiceImpl implements CardService {
    @Resource
    private CardMapper cardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserToRoleMapper userToRoleMapper;

    @Transactional
    @Override
    public RResult useCard(RResult result, UseCardParam param) {

        UpdateWrapper<Card> ew = new UpdateWrapper<>();
        ew.eq("cardnum", param.getCardNum());
        List<Card> cards = cardMapper.selectList(ew);
        if(cards.size() == 0){
            result.setMessage("该充值卡号不存在");
            return result;
        }
        Card card = cards.get(0);
        if(card.getState() == -1){
            result.setMessage("该充值卡号不存在");
            return result;
        }
        if(card.getState() == 0){
            result.setMessage("该充值卡号已被封");
            return result;
        }
        if(card.getState() == 2){
            result.setMessage("该充值卡已被使用");
            return result;
        }

        UpdateWrapper<User> uew = new UpdateWrapper<>();
        uew.eq("username", param.getUsername());
        List<User> users = userMapper.selectList(uew);
        if(users.size() == 0){
            result.setMessage("没找到当前需要充值的用户");
            return result;
        }
        User user = users.get(0);

        //判断中假表
        Date vipendtime = user.getVipendtime();
        if(null == vipendtime){
            vipendtime = new Date();
        }
        Date newdate = new Date();
        long endtime =vipendtime.getTime();
        long newtime= newdate.getTime();
        if(endtime > newtime){
            //如果账户时间大于当前时间，就还是vip就是叠加时间
            newdate = vipendtime;
        }else{
            user.setVipstatetime(newdate);
        }

        //字符串转数组
        Boolean roleBoolean = false;
        String replace = user.getRolelistid().replace("[", "").replace("]", "");
        List<String> roles = new ArrayList<>();
        if(replace.indexOf(",") > -1){
            String[] split = replace.split(",");
//            roles = Arrays.asList(split);

            if(null != split && split.length > 0){

                for (int i = 0; i < split.length; i++) {
                    String role = split[i];
                    if(!role.trim().equals("1") && !role.trim().equals("2") && !role.trim().equals("3")){
                        roles.add(role);
                    }else{
                        roleBoolean = true;
                    }
                }
            }

        }else{
            if(card.getCardsupertype() == 1 && !replace.equals("1") || card.getCardsupertype() == 2 && !replace.equals("2") || card.getCardsupertype() == 3 && !replace.equals("3")){
                roleBoolean = true;
            }else if(StringUtils.isNoneBlank(replace)){
                roles.add(replace);
            }
        }

        if(roleBoolean){
            String index = "0";
            if(card.getCardsupertype() == 1){
                index = "1";
            }else if(card.getCardsupertype() == 2){
                index = "2";
            }else if(card.getCardsupertype() == 3) {
                index = "3";
            }

            if(!"0".equals(index)){
                roles.add(index);
                UpdateWrapper<Usertorole> we = new UpdateWrapper<>();
                we.eq("userid", user.getId());
                we.eq("roleid", "1");
                we.or().eq("userid", user.getId());
                we.eq("roleid", "2");
                we.or().eq("userid", user.getId());
                we.eq("roleid", "3");

                List<Usertorole> usertoroles = userToRoleMapper.selectList(we);
                if(usertoroles.size() == 0){
                    Usertorole usertorole = new Usertorole();
                    usertorole.setUserid(user.getId() + "");
                    usertorole.setRoleid(card.getCardsupertype()+"");
                    userToRoleMapper.insert(usertorole);
                }else{
                    Usertorole usertorole = usertoroles.get(0);
                    UpdateWrapper<Usertorole> ruw = new UpdateWrapper<>();
                    ruw.eq("id", usertorole.getId());
                    usertorole.setRoleid(card.getCardsupertype()+"");
                    userToRoleMapper.update(usertorole, ruw);
                }
            }

            user.setRolelistid(roles.toString().trim());
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        if(card.getCardtype() == 2){
            // 2=季卡
            cal.add(Calendar.DATE, 90);
            Date date = cal.getTime();
            user.setVipendtime(date);
        }else if(card.getCardtype() == 3){
            // 3=年卡
            cal.add(Calendar.DATE, 365);
            Date date = cal.getTime();
            user.setVipendtime(date);
        }else{
            //如果都不是就按照月卡充值
            cal.add(Calendar.DATE, 30);
            Date date = cal.getTime();
            user.setVipendtime(date);
        }
        userMapper.updateById(user);


        UpdateWrapper<Card> cew = new UpdateWrapper<>();
        cew.eq("ssid", card.getSsid());
        card.setState(2);//设置为已经使用
        card.setTupupid(param.getTupupssid());
        card.setUserid(user.getSsid());
        card.setEmploytime(new Date());
        boolean update = card.update(cew);
        if (update) {
            result.setMessage("充值成功！");
            result.changeToTrue(update);
        }

        return result;
    }

    @Override
    public RResult getCard(RResult result) {
        UpdateWrapper<Card> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("createtime");
        List<Card> cards = cardMapper.selectList(ew);
        result.changeToTrue(cards);
        return result;
    }

    @Override
    public RResult getCardByssid(RResult result, DeleteCardParam param) {
        UpdateWrapper<Card> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Card> cards = cardMapper.selectList(ew);
        if (null != cards && cards.size() > 0) {
            Card card = cards.get(0);
            result.changeToTrue(card);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getCardPage(RResult result, GetCardPageParam param) {

        GetCardVO cardVO = new GetCardVO();

        UpdateWrapper<Card> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getCardnum())){
            ew.like("c.cardnum", param.getCardnum());
        }
        if(null != param.getCardtype() && param.getCardtype() > 0){
            ew.eq("c.cardtype", param.getCardtype());
        }
        if(StringUtils.isNotEmpty(param.getParentname())){
            ew.like("up.username", param.getParentname());
        }
        if(StringUtils.isNotEmpty(param.getTopup())){
            ew.like("ut.username", param.getTopup());
        }
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("u.username", param.getUsername());
        }
        if(StringUtils.isNotEmpty(param.getEmploytime())){
            ew.le("c.employtime", param.getEmploytime());
        }

        ew.orderByAsc("c.cardtype");
        ew.orderByDesc("c.createtime");

        Integer count = cardMapper.selectCardCount(ew);
        param.setRecordCount(count);

        Page<Card> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Card> sqCacheList = cardMapper.getCardPage(page, ew);

        cardVO.setPagelist(sqCacheList.getRecords());
        cardVO.setPageparam(param);

        result.changeToTrue(cardVO);
        return result;
    }

    //批量生成充值卡
    @Override
    public RResult addCard(RResult result, AddUpdateCardParam param) {

        //类型和数量
        for (int i = 0; i < param.getCardsize(); i++) {
            long cardNum = IdWorker.getId();
            UpdateWrapper<Card> ew = new UpdateWrapper<>();
            ew.eq("cardnum", cardNum);
            List<Card> TypeList = cardMapper.selectList(ew);
            if (TypeList.size() == 0) {
                //生成ssid
                //用户id
                //充值卡类型
                String ssid = OpenUtil.getUUID_32();
                Card card = new Card();
                if(StringUtils.isNoneBlank(param.getCardprefix())){
                    card.setCardnum(param.getCardprefix().trim() + cardNum);
                }else{
                    card.setCardnum(cardNum + "");
                }
                card.setCardtype(param.getCardtype());
                card.setCardsupertype(param.getCardsupertype());
                card.setSsid(ssid);
                card.setParentuserid("1");
                int insert = cardMapper.insert(card);
                if (insert > 0) {
                    result.changeToTrue(insert);
                }
            }else{
                i--;
            }
        }

        return result;
    }

    @Override
    public RResult updateCard(RResult result, AddUpdateCardParam param) {
        //先校验是否已经存在
        UpdateWrapper<Card> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("cardnum", param.getCardnum());
        ewcheck.ne("ssid", param.getSsid());
        List<Card> cards = cardMapper.selectList(ewcheck);
        if (null != cards && cards.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Card> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Card card = new Card();
        card.setCardnum(param.getCardnum());
        card.setCardtype(param.getCardtype());
        card.setCardsupertype(param.getCardsupertype());
        card.setSsid(param.getSsid());
        card.setState(param.getState());

        int update = cardMapper.update(card, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getCardnum());

        return result;
    }

    @Override
    public RResult deleteCard(RResult result, DeleteCardParam param) {
        UpdateWrapper<Card> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Card card = cardMapper.selectOne(ew);
        if(null == card){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = cardMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + card.getCardnum());
        return result;
    }
}