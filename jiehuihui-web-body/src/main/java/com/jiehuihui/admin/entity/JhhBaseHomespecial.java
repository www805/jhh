package com.jiehuihui.admin.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * (JhhBaseHomespecial)实体类
 *
 * @author zhuang
 * @since 2020-07-09 18:29:15
 */

public class JhhBaseHomespecial implements Serializable {
    private static final long serialVersionUID = 401975492567758536L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 特价标题
     */
    private String specialtitle;
    /**
     * 封面图片集合
     */
    private String fmimglist;
    /**
     * 图片上标签文字
     */
    private String imgtag;
    /**
     * 标题下方描述
     */
    private String titledescribe;
    /**
     * 原价
     */
    private Double oldprice;
    /**
     * 当前价钱
     */
    private Double nowprice;
    /**
     * 下单指南json形式
     */
    private String ordertext;
    /**
     * 活动介绍
     */
    private String hddescribe;
    /**
     * 活动图片集合
     */
    private String hdimglist;
    /**
     * 任务说明
     */
    private String tasktext;
    /**
     * 任务介绍
     */
    private String taskdescribe;
    /**
     * 任务图片集合
     */
    private String taskimglist;
    /**
     * 优惠标签集
     */
    private String specialtags;
    /**
     * 进店描述标签
     */
    private String gototag;
    /**
     * 点赞数量
     */
    private Long likesize;
    /**
     * 置顶
     */
    private Integer topnum;
    /**
     * 首页置顶
     */
    private Integer hometopnum;
    /**
     * 公告关键字
     */
    private String shopid;
    /**
     * 关联城市ssid
     */
    private String cityid;
    /**
     * 排序
     */
    private Integer sortnum;
    /**
     * 唯一id
     */
    private String ssid;
    /**
     * 设置优惠时间
     */
    private String settime;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 状态 0禁用 1正常 2删除
     */
    private Integer state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialtitle() {
        return specialtitle;
    }

    public void setSpecialtitle(String specialtitle) {
        this.specialtitle = specialtitle;
    }

    public String getFmimglist() {
        return fmimglist;
    }

    public void setFmimglist(String fmimglist) {
        this.fmimglist = fmimglist;
    }

    public String getImgtag() {
        return imgtag;
    }

    public void setImgtag(String imgtag) {
        this.imgtag = imgtag;
    }

    public String getTitledescribe() {
        return titledescribe;
    }

    public void setTitledescribe(String titledescribe) {
        this.titledescribe = titledescribe;
    }

    public Double getOldprice() {
        return oldprice;
    }

    public void setOldprice(Double oldprice) {
        this.oldprice = oldprice;
    }

    public Double getNowprice() {
        return nowprice;
    }

    public void setNowprice(Double nowprice) {
        this.nowprice = nowprice;
    }

    public String getOrdertext() {
        return ordertext;
    }

    public void setOrdertext(String ordertext) {
        this.ordertext = ordertext;
    }

    public String getHddescribe() {
        return hddescribe;
    }

    public void setHddescribe(String hddescribe) {
        this.hddescribe = hddescribe;
    }

    public String getHdimglist() {
        return hdimglist;
    }

    public void setHdimglist(String hdimglist) {
        this.hdimglist = hdimglist;
    }

    public String getTasktext() {
        return tasktext;
    }

    public void setTasktext(String tasktext) {
        this.tasktext = tasktext;
    }

    public String getTaskdescribe() {
        return taskdescribe;
    }

    public void setTaskdescribe(String taskdescribe) {
        this.taskdescribe = taskdescribe;
    }

    public String getTaskimglist() {
        return taskimglist;
    }

    public void setTaskimglist(String taskimglist) {
        this.taskimglist = taskimglist;
    }

    public String getSpecialtags() {
        return specialtags;
    }

    public void setSpecialtags(String specialtags) {
        this.specialtags = specialtags;
    }

    public String getGototag() {
        return gototag;
    }

    public void setGototag(String gototag) {
        this.gototag = gototag;
    }

    public Long getLikesize() {
        return likesize;
    }

    public void setLikesize(Long likesize) {
        this.likesize = likesize;
    }

    public Integer getTopnum() {
        return topnum;
    }

    public void setTopnum(Integer topnum) {
        this.topnum = topnum;
    }

    public Integer getHometopnum() {
        return hometopnum;
    }

    public void setHometopnum(Integer hometopnum) {
        this.hometopnum = hometopnum;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public Integer getSortnum() {
        return sortnum;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getSettime() {
        return settime;
    }

    public void setSettime(String settime) {
        this.settime = settime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}