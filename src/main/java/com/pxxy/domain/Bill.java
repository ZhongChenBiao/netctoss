package com.pxxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Descricption:账单的实体类
 * @Author:江灿
 * @Date:Create in 10:22 2019/5/29
 */
public class Bill implements Serializable {
    private Integer billId;
    private Integer accountId;//该账单所对应的账户id
    private Integer costId;//该账单对应的资费id
    private String payway;//表示该账单的支付方式，1现金，2微信支付，3支付宝，4网银转账
    private String status;//该账单的状态，0已支付，1未支付
    private String charge;//该账单对应的cost表中的费用
    private Date createTime;//账单的生成时间

    //账单所属的账户，映射关系：多对一
    private Account account;


    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
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

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", accountId=" + accountId +
                ", costId=" + costId +
                ", payway='" + payway + '\'' +
                ", status='" + status + '\'' +
                ", charge='" + charge + '\'' +
                ", createTime=" + createTime +
                ", account=" + account +
                '}';
    }
}
