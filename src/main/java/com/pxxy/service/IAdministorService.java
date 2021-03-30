package com.pxxy.service;

import com.github.pagehelper.PageInfo;
import com.pxxy.domain.Administor;
import com.pxxy.domain.ListAdministor;
import com.pxxy.domain.PageResult;
import com.pxxy.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * @Descricption:业务层管理员接口
 * @Author:江灿
 * @Date:Create in 11:04 2019/5/29
 */
public interface IAdministorService {
   /*
    * @Author:江灿
    * @Description:管理员登录
    * @Date: 17:22 2019/5/30
    * @Param: [administor]
    * @return: com.pxxy.domain.Administor
    **/
    Administor login(Administor administor);

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
     * @Description:校验旧密码
     * @Date: 9:54 2019/6/3
     * @Param: [id, oldPassword]
     * @return: java.lang.Boolean
     **/
    Boolean checkOldPassword(int id, String oldPassword);

    /**
     * @Author:江灿
     * @Description:修改密码
     * @Date: 10:38 2019/6/3
     * @Param: [administor]
     * @return: void
     **/
    void updatePassword(Administor administor);

    /**
     * @Author:江灿
     * @Description:查询管理员的信息
     * @Date: 8:18 2019/6/4
     * @Param: [id]
     * @return: com.pxxy.domain.Administor
     **/
    Administor selectAdministor(int id);

    /**
     * @Author:江灿
     * @Description:修改管理员信息
     * @Date: 10:17 2019/6/4
     * @Param: [administor]
     * @return: void
     **/
    void modifyMessage(Administor administor);

   /**
    * @Author:江灿
    * @Description:查询所有管理员
    * @Date: 14:38 2019/6/4
    * @Param: [currentPage]
    * @return: com.pxxy.domain.PageResult<com.pxxy.domain.Administor>
    **/
    PageResult<Administor> findAll(int currentPage);

    /**
     * @Author:江灿
     * @Description:根据管理员id查询其详细信息，以及所有的角色
     * @Date: 15:14 2019/6/4
     * @Param: [id]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     **/
    Map<String, Object> getAdministorAndRoles(int id);

    /**
     * @Author:江灿
     * @Description:管理员的信息被其他管理员修改
     * @Date: 16:44 2019/6/4
     * @Param: [administor, roleIds]
     * @return: void
     **/
    void modifiedMessage(Administor administor, List<Integer> roleIds);

    /**
     * @Author:江灿
     * @Description:重置密码
     * @Date: 17:11 2019/6/4
     * @Param: [administors]
     * @return: void
     **/
    void resetPassword(List<Administor> administors);

    /**
     * @Author:江灿
     * @Description:删除管理员
     * @Date: 15:14 2019/6/5
     * @Param: [id]
     * @return: void
     **/
    void deleteAdministor(int id);

    /**
     * @Author:江灿
     * @Description:查询所有角色信息
     * @Date: 15:31 2019/6/5
     * @Param: []
     * @return: java.util.List<com.pxxy.domain.Role>
     **/
    List<Role> preAdd();

    /**
     * @Author:江灿
     * @Description:增加管理员
     * @Date: 15:32 2019/6/5
     * @Param: [administor, roleIds]
     * @return: void
     **/
    void addAdministor(Administor administor,List<Integer> roleIds);

    /*
     * @Author:江灿
     * @Description:根据功能模块筛选管理员
     * @Date: 11:10 2019/6/6
     * @Param: []
     * @return: com.pxxy.domain.PageResult<com.pxxy.domain.Administor>
     **/
    PageResult<Administor> selectAdministorByModuleId(int currentPage, int moduleId);

    /**
     * @Author:江灿
     * @Description:按角色名称筛选管理员
     * @Date: 14:55 2019/6/6
     * @Param: [currentPage, name]
     * @return: com.pxxy.domain.PageResult<com.pxxy.domain.Administor>
     **/
    PageResult<Administor> selectAdministorByRoleName(int currentPage, String  name);
}
