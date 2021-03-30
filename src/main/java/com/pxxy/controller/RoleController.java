package com.pxxy.controller;

import com.pxxy.domain.Administor;
import com.pxxy.domain.PageResult;
import com.pxxy.domain.ResponseResult;
import com.pxxy.domain.Role;
import com.pxxy.service.IRoleService;
import com.pxxy.service.ex.AdministorServiceException;
import com.pxxy.service.ex.RoleServiceException;
import com.pxxy.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Descricption:角色的表现层
 * @Author:江灿
 * @Date:Create in 11:14 2019/5/29
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/getAllRole")
    @ResponseBody
    public ResponseResult<PageResult> getAllRole(Integer currentPage){
        if(currentPage==null){//首次访问时，直接定位在第一页
            currentPage = 1;
        }
        try {
            PageResult<Role> pageResult = roleService.findAllAndModule(currentPage);
            return new ResponseResult<>(0,"",pageResult);
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:增加角色
     * 备注：未测试
     * @Date: 9:16 2019/6/6
     * @Param: [name, moduleIds]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/addRole")
    @ResponseBody
    public ResponseResult<Void> addRole(String name, List<Integer> moduleIds){
        if(name.isEmpty()||name==""){
            return new ResponseResult<>(1,"角色名称不能为空！");
        }
        if(moduleIds.size() <= 0){
            return new ResponseResult<>(1,"至少选择一项权限！");
        }
        try {
            roleService.addRole(name, moduleIds);
            return new ResponseResult<>(0,"添加成功！");
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /*
     * @Author:江灿
     * @Description:删除角色
     * @Date: 9:32 2019/6/6
     * @Param: [roleId]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/deleteRole")
    @ResponseBody
    public ResponseResult<Void> deleteRole(int roleId){
        try {
            roleService.deleteRole(roleId);
            return new ResponseResult<>(1,"删除成功！");
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:根据id获得角色及其权限信息
     * @Date: 10:20 2019/6/6
     * @Param: [roleId]
     * @return: com.pxxy.domain.ResponseResult<com.pxxy.domain.Role>
     **/
    @RequestMapping("/preModifyRole")
    @ResponseBody
    public ResponseResult<Role> preModifyRole(int roleId){
        try {
            Role role =  roleService.findRoleById(roleId);
            return new ResponseResult<>(0,"",role);
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:修改角色信息
     * @Date: 10:35 2019/6/6
     * @Param: [role, moduleIds]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/ModifyRole")
    @ResponseBody
    public ResponseResult<Void> ModifyRole(Role role, List<Integer> moduleIds){
        try {
            roleService.ModifyRole(role, moduleIds);
            return new ResponseResult<>(0,"保存成功！");
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }
}
