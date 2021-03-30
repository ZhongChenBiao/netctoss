package com.pxxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxy.domain.Administor;
import com.pxxy.domain.Module;
import com.pxxy.domain.PageResult;
import com.pxxy.domain.Role;
import com.pxxy.mapper.ModuleMapper;
import com.pxxy.mapper.RoleMapper;
import com.pxxy.service.IRoleService;
import com.pxxy.service.ex.AdministorServiceException;
import com.pxxy.service.ex.RoleServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Descricption:业务层角色接口实现类
 * @Author:江灿
 * @Date:Create in 11:10 2019/5/29
 */
@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public PageResult<Role> findAllAndModule(int currentPage) {
        PageHelper.startPage(currentPage,10);
        List<Role> roles = roleMapper.findAllAndModule();
        if(roles.size() <= 0 ){
            throw new RoleServiceException("数据并发出错，请稍后重试！");
        }
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        PageResult pageResult = new PageResult<Role>();
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setItems(pageInfo.getList());
        return pageResult;
    }

    @Override
    public void addRole(String name, List<Integer> moduleIds) {
        Integer roleId = roleMapper.addRole(name);
        if(roleId == null){
            throw new RoleServiceException("后台系统繁忙，请稍后重试！");
        }
        for(int moduleId : moduleIds){
            int row = roleMapper.addModuleForRole(roleId, moduleId);
            if(row != 0 ){
                throw new RoleServiceException("后台系统繁忙，请稍后重试！");
            }
        }
    }

    @Override
    public void deleteRole(int roleId) {
        //删除角色被赋予的权限
        int rows = roleMapper.deleteMiddleModule(roleId);
        if(rows <= 0 ){
            throw new RoleServiceException("后台系统繁忙，请稍后重试！");
        }
        //删除管理员被赋予的角色
        int row1 = roleMapper.deleteRoleForAdministor(roleId);
        //删除角色
        int row = roleMapper.deleteRoleById(roleId);
        if(row !=1 ){
            throw new RoleServiceException("后台系统繁忙，请稍后重试！");
        }
    }

    @Override
    public Role findRoleById(int roleId) {
        Role role = roleMapper.findRoleById(roleId);
        if(role == null){
            throw new RoleServiceException("后台系统繁忙，请稍后重试！");
        }
        //查询角色所拥有的权限的id
        List moduleIds = roleMapper.findAllModuleIds(roleId);
        List<Module> modules = moduleMapper.findAllModule(moduleIds);
        if(modules.size() <= 0){
            throw new RoleServiceException("后台系统繁忙，请稍后重试！");
        }
        role.setModules(modules);
        return role;
    }

    @Override
    public void ModifyRole(Role role, List<Integer> moduleIds) {
        int row = roleMapper.ModifyRole(role);
        if(row != 1){
            throw new RoleServiceException("后台繁忙，保存失败，请稍后重试！");
        }
        //先删除角色所拥有的权限
        int row1 = roleMapper.deleteMiddleModule(role.getRoleId());
        if(row1 <=0 ){
            throw new RoleServiceException("后台繁忙，保存失败，请稍后重试！");
        }
        //保存角色最新的权限
        for(int moduleId : moduleIds){
            int row2 = roleMapper.updateModule(role.getRoleId(), moduleId);
            if(row2 != 1){
                throw new RoleServiceException("后台繁忙，保存失败，请稍后重试！");
            }
        }
    }

}
