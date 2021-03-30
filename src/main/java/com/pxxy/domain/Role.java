package com.pxxy.domain;

import java.io.Serializable;
import java.util.List;
/**
 * @Descricption:角色实体类
 * @Author:江灿
 * @Date:Create in 10:22 2019/5/29
 */
public class Role implements Serializable {
    private Integer roleId;
    private String name;//角色的名称

    //角色拥有的权限，映射关系：多对多
    private List<Module> modules;

    //角色所属的管理员,映射关系：多对多
    private List<Administor> administors;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Administor> getAdministors() {
        return administors;
    }

    public void setAdministors(List<Administor> administors) {
        this.administors = administors;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", modules=" + modules +
                ", administors=" + administors +
                '}';
    }
}
