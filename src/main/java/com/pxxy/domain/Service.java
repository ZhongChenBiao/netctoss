package com.pxxy.domain;

import java.sql.Date;

/**
 * @Descricption:账户业务的实体类
 * @Author:江灿
 * @Date:Create in 10:23 2019/5/29
 */
public class Service {
    private Integer serviceId;
    private Integer accountId;
    private Integer costId;
    private String serviceName;//登录服务器的账号,即业务账号
    private String servicePassword;//登录服务器的密码
    private Integer status;//表示该业务的状态，0开通,1暂停,2删除
    private String unixHost;//服务器的ip地址
    private Date createTime;//该业务账号创建的时间
    //业务账号所属的账户,映射关系：一对一
    private Account account;
    //业务账号对应的资费,映射关系：一对一
    private Cost cost;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", accountId=" + accountId +
                ", costId=" + costId +
                ", serviceName='" + serviceName + '\'' +
                ", servicePassword='" + servicePassword + '\'' +
                ", status='" + status + '\'' +
                ", unixHost='" + unixHost + '\'' +
                ", createTime=" + createTime +
                ", account=" + account +
                ", cost=" + cost +
                '}';
    }
}
