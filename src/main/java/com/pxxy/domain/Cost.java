package com.pxxy.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @Descricption:资费的实体类
 * @Author:江灿
 * @Date:Create in 10:14 2019/5/29
 */
public class Cost implements Serializable {
    private Integer costId;
    private String name;//资费的名称
    private Float baseDuration;//资费基本时长
    private Double baseCost;//基本费用
    private Double unitCost;//单位费用
    private Integer status;//该资费的状态，0启用，1暂停，2删除
    private String descr;//对该资费的一个大致描述
    private Date createTime;
    private Date startTime;//表示该资费最新的启用时间
    private String costType;//资费的类型，1包月，2套餐，3计时

    //资费所对应的业务，映射关系：一对多
    private List<Service> services;

    //资费所对应的账单，映射关系：一对多
    private List<Bill> bills;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(Float baseDuration) {
        this.baseDuration = baseDuration;
    }

    public Double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "costId=" + costId +
                ", name='" + name + '\'' +
                ", baseDuration=" + baseDuration +
                ", baseCost=" + baseCost +
                ", unitCost=" + unitCost +
                ", status='" + status + '\'' +
                ", descr='" + descr + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", costType='" + costType + '\'' +
                ", services=" + services +
                ", bills=" + bills +
                '}';
    }
}
