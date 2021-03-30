package com.pxxy.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Descricption:权限模板实体类
 * @Author:江灿
 * @Date:Create in 10:22 2019/5/29
 */
public class Module implements Serializable {
    private Integer moduleId;
    private String name;//权限名称

    //权限所属的角色，映射关系：多对多
    private List<Role> roles;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
