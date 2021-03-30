package com.pxxy.domain;

import sun.security.util.Password;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @Descricption:管理员的实体类
 * @Author:江灿
 * @Date:Create in 10:22 2019/5/29
 */
public class Administor implements Serializable {
    private Integer adminId;
    private String adminNumber;//管理员登录时所用的账号
    private String password;
    private String name;//管理员的真实姓名
    private String telephone;
    private String email;
    private Date createTime;
    private Integer status;//管理员的状态,0正常,2删除

    //管理员所拥有的角色，映射关系：多对多
    private List<Role> roles;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Administor{" +
                "adminId=" + adminId +
                ", adminNumber='" + adminNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
