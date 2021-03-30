package com.pxxy.service;

import com.pxxy.domain.PageResult;
import com.pxxy.domain.Role;

import java.util.List;

/**
 * @Descricption:业务层角色接口
 * @Author:江灿
 * @Date:Create in 11:09 2019/5/29
 */
public interface IRoleService {
    /*
     * @Author:江灿
     * @Description:查询所有角色及其所对应的权限
     * @Date: 17:38 2019/6/5
     * @Param: [currentPage]
     * @return: com.pxxy.domain.PageResult<com.pxxy.domain.Role>
     **/
    PageResult<Role> findAllAndModule(int currentPage);

    /**
     * @Author:江灿
     * @Description:添加角色
     * @Date: 8:37 2019/6/6
     * @Param: [name, moduleIds]
     * @return: void
     **/
    void addRole(String name, List<Integer> moduleIds);

    /**
     * @Author:江灿
     * @Description:删除角色
     * @Date: 9:22 2019/6/6
     * @Param: [roleId]
     * @return: void
     **/
    void deleteRole(int roleId);

    /*
     * @Author:江灿
     * @Description:查询角色及其权限
     * @Date: 9:42 2019/6/6
     * @Param: [roleId]
     * @return: com.pxxy.domain.Role
     **/
    Role findRoleById(int roleId);

    /**
     * @Author:江灿
     * @Description:修改角色及其权限信息
     * @Date: 10:24 2019/6/6
     * @Param: [role, moduleIds]
     * @return: void
     **/
    void ModifyRole(Role role, List<Integer> moduleIds);
}
