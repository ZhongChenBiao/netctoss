package com.pxxy.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
/**
 * @Descricption:账户的实体类
 * @Author:江灿
 * @Date:Create in 10:22 2019/5/29
 */
public class Account implements Serializable {
    private Integer accountId;
    private String loginName;//账户的登录名
    private String loginPassword;//账户的登录密码
    private String status;//账户的状态，0开通，1暂停使用
    private String name;//账户人的真实姓名
    private String accountCard;//账户的身份证号
    private String gender;//账户人的性别，0男，1女
    private String telephone;
    private String email;
    private String address;
    private String qq;
    private Date createTime;
    private Date lastLoginTime;//账户的最近登录时间
    private Date lastExitTime;//账户的最近退出时间

    //账户的账单，映射关系：一对多
    private List<Bill> bills;
    //账户的业务账号,映射关系：一对多
    private List<Service> services;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_card() {
        return accountCard;
    }

    public void setAccount_card(String account_card) {
        this.accountCard = account_card;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastExitTime() {
        return lastExitTime;
    }

    public void setLastExitTime(Date lastExitTime) {
        this.lastExitTime = lastExitTime;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", account_card='" + accountCard + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", lastExitTime=" + lastExitTime +
                ", bills=" + bills +
                ", services=" + services +
                '}';
    }
}
