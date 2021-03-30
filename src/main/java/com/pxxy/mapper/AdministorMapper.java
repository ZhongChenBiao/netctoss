package com.pxxy.mapper;

import com.alibaba.fastjson.support.odps.udf.CodecCheck.A;
import com.pxxy.domain.Administor;
import com.pxxy.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Descricption:管理员的持久层接口
 * @Author:江灿
 * @Date:Create in 11:19 2019/5/29
 */
@Repository()
public interface AdministorMapper {
    /*
     * @Author:江灿
     * @Description:根据管理员登录账号查询
     * @Date: 17:16 2019/5/30
     * @Param: [adminNumber]
     * @return: com.pxxy.domain.Administor
     **/
    Administor findByAdminNumber(String adminNumber);

    /**
     * @Author:江灿
     * @Description:根据id查询管理员所拥有的权限
     * @Date: 8:56 2019/6/3
     * @Param: [id]
     * @return: java.util.List
     **/
    List findAuthorityById(int id);

    /**
     * @Author:江灿
     * @Description:根据id查询管理员的密码
     * @Date: 10:01 2019/6/3
     * @Param: [id]
     * @return: java.lang.String
     **/
    String findPasswordById(int id);

   /**
    * @Author:江灿
    * @Description:修改密码
    * @Date: 10:3910:39 2019/6/3
    * @Param: [administor]
    * @return: int
    **/
    Integer updatePassword(Administor administor);

    /**
     * @Author:江灿
     * @Description:根据id查询该管理员所拥有的角色的id集合
     * @Date: 17:00 2019/6/3
     * @Param: [id]
     * @return: java.util.List
     **/
    List findRoleIdById(int id);

    /**
     * @Author:江灿
     * @Description:根据id查询管理员的信息
     * @Date: 16:17 2019/6/3
     * @Param: [id]
     * @return: com.pxxy.domain.Administor
     **/
    Administor findById(int id);

    /**
     * @Author:江灿
     * @Description:根据管理员id查询所拥有的角色id集合
     * @Date: 8:13 2019/6/4
     * @Param: [id]
     * @return: java.util.List
     **/
    List findRoleIdsById(int id);

    /**
     * @Author:江灿
     * @Description:修改管理员信息
     * @Date: 10:20 2019/6/4
     * @Param: [administor]
     * @return: java.lang.Integer
     **/
    Integer updateMessage(Administor administor);

    /**
     * @Author:江灿
     * @Description:查询所有管理员
     * @Date: 11:05 2019/6/4
     * @Param: []
     * @return: java.util.List<com.pxxy.domain.Administor>
     **/
    List<Administor> findAll();

    /**
     * @Author:江灿
     * @Description:删除管理员所有的角色
     * @Date: 16:41 2019/6/4
     * @Param: [id]
     * @return: void
     **/
    void deleteAllRole(int id);

    /**
     * @Author:江灿
     * @Description:更新管理员的角色信息
     * @Date: 17:12 2019/6/4
     * @Param: [administorId, roleId]
     * @return: int
     **/
    int updateRoleMessage(@Param("administorId")int administorId, @Param("roleId")int roleId);

    /**
     * @Author:江灿
     * @Description:删除管理员，将其status设置为2，2表示删除
     * @Date: 17:28 2019/6/4
     * @Param: [id]
     * @return: int
     **/
    int deleteById(int id);

    /**
     * @Author:江灿
     * @Description:增加管理员
     * @Date: 15:24 2019/6/5
     * @Param: [administor]
     * @return: int
     **/
    int addAdministor(Administor administor);

    /**
     * @Author:江灿
     * @Description:筛选拥有权限id为moduleId的角色id集合
     * @Date: 11:23 2019/6/6
     * @Param: [roleIds, moduleId]
     * @return: java.util.List
     **/
    List findRoleIdsByModuleId(@Param("list") List roleIds, @Param("moduleId") int moduleId);

    /*
     * @Author:江灿
     * @Description:按照角色id查询拥有该角色的所有管理员的id
     * @Date: 15:18 2019/6/6
     * @Param: [roleId]
     * @return: java.util.List
     **/
    List findAdminIdByRoleId(int roleId);

    /*
     * @Author:江灿
     * @Description:查询id集合中的所有管理员
     * @Date: 15:21 2019/6/6
     * @Param: [adminIds]
     * @return: java.util.List
     **/
    List findByIds(List adminIds);

}
