package com.pxxy.mapper;

import com.pxxy.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Descricption:角色的持久层接口
 * @Author:江灿
 * @Date:Create in 11:21 2019/5/29
 */
@Repository()
public interface RoleMapper {
    /**
     * @Author:江灿
     * @Description:根据id查询
     * @Date: 16:47 2019/6/3
     * @Param: [id]
     * @return: com.pxxy.domain.Role
     **/
    Role findById(int id);

    /**
     * @Author:江灿
     * @Description:根据遍历list中的id查询
     * @Date: 8:11 2019/6/4
     * @Param: [ids]
     * @return: java.util.List<com.pxxy.domain.Role>
     **/
    List<Role> findByList(List ids);

    /**
     * @Author:江灿
     * @Description:查询所有的角色
     * @Date: 15:18 2019/6/4
     * @Param: []
     * @return: java.util.List<com.pxxy.domain.Role>
     **/
    List<Role> findAll();

    /**
     * @Author:江灿
     * @Description:查询所有角色及其所对应的权限
     * @Date: 17:31 2019/6/5
     * @Param: []
     * @return: java.util.List<com.pxxy.domain.Role>
     **/
    List<Role> findAllAndModule();

    /**
     * @Author:江灿
     * @Description:添加角色
     * @Date: 9:00 2019/6/6
     * @Param: [name]
     * @return: java.lang.Integer
     **/
    Integer addRole(String name);

    /**
     * @Author:江灿
     * @Description:给角色赋予权限
     * @Date: 9:01 2019/6/6
     * @Param: [moduleIds]
     * @return: int
     **/
    int addModuleForRole(@Param("roleId") int roleId,@Param("moduleId") int moduleId);

    /**
     * @Author:江灿
     * @Description:删除角色被赋予的权限
     * @Date: 9:25 2019/6/6
     * @Param: [roleId]
     * @return: int
     **/
    int deleteMiddleModule(int roleId);

    /**
     * @Author:江灿
     * @Description:删除管理员被赋予的角色
     * @Date: 9:29 2019/6/6
     * @Param: [roleId]
     * @return: int
     **/
    int deleteRoleForAdministor(int roleId);

    /**
     * @Author:江灿
     * @Description:删除角色
     * @Date: 9:26 2019/6/6
     * @Param: [roleId]
     * @return: int
     **/
    int deleteRoleById(int roleId);

    /**
     * @Author:江灿
     * @Description:查询角色
     * @Date: 9:43 2019/6/6
     * @Param: [roleId]
     * @return: com.pxxy.domain.Role
     **/
    Role findRoleById(int roleId);

    /**
     * @Author:江灿
     * @Description:查询角色拥有权限的id
     * @Date: 10:06 2019/6/6
     * @Param: [roleId]
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> findAllModuleIds(int roleId);

    /**
     * @Author:江灿
     * @Description:修改角色信息
     * @Date: 10:31 2019/6/6
     * @Param: [role]
     * @return: int
     **/
    int ModifyRole(Role role);

    /*
     * @Author:江灿
     * @Description:更新权限
     * @Date: 10:33 2019/6/6
     * @Param: [moduleId]
     * @return: int
     **/
    int updateModule(@Param("roleId") int roleId, @Param("moduleId") int moduleId);

    /*
     * @Author:江灿
     * @Description:筛选角色名为name的角色
     * @Date: 15:00 2019/6/6
     * @Param: [roleIds, name]
     * @return: java.util.List<com.pxxy.domain.Role>
     **/
    List<Role> findRoleIdsByRoleName(@Param("list") List roleIds, @Param("name") String name);

    /*
     * @Author:江灿
     * @Description:查询名称为name的角色的id
     * @Date: 15:16 2019/6/6
     * @Param: [name]
     * @return: int
     **/
    Integer findRoleIdByName(String name);

    /*
     * @Author:江灿
     * @Description:按模块id来查询拥有该权限角色的id
     * @Date: 16:30 2019/6/6
     * @Param: [moduleId]
     * @return: java.lang.Integer
     **/
    Integer findRoleIdByModuleId(int moduleId);
}
