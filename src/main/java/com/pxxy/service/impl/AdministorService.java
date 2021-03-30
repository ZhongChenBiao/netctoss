package com.pxxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxy.domain.*;
import com.pxxy.mapper.AdministorMapper;
import com.pxxy.mapper.ModuleMapper;
import com.pxxy.mapper.RoleMapper;
import com.pxxy.service.IAdministorService;
import com.pxxy.service.ex.AdministorServiceException;
import com.pxxy.service.ex.RoleServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descricption:业务层管理员接口实现类
 * @Author:江灿
 * @Date:Create in 11:05 2019/5/29
 */
@Service("administorService")
public class AdministorService implements IAdministorService {
    @Autowired
    private AdministorMapper administorMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public Administor login(Administor administor) {
        Administor administor1 = administorMapper.findByAdminNumber(administor.getAdminNumber());
        if(administor1 == null){
            throw new AdministorServiceException("该管理员不存在");
        }
        if(!(DigestUtils.md5Hex(administor.getPassword().getBytes())).equals(administor1.getPassword())){
            throw new AdministorServiceException("管理员账号或密码错误");
        }
        return administor1;
    }

    @Override
    public List findAuthorityById(int id) {
        List authority = administorMapper.findAuthorityById(id);
        return authority;
    }

    @Override
    public Boolean checkOldPassword(int id, String oldPassword) {
        String password = administorMapper.findPasswordById(id);
        //比对密码
        if(password.equals(DigestUtils.md5Hex(oldPassword.getBytes()))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void updatePassword(Administor administor) {
        administor.setPassword(DigestUtils.md5Hex(administor.getPassword().getBytes()));
        Integer row= administorMapper.updatePassword(administor);
        System.out.println("row:"+row);
        if(row != 1){//若影响的记录超过一行，则抛异常回滚
            throw new AdministorServiceException("数据并发出错，请稍后重试！");
        }
    }

    @Override
    public Administor selectAdministor(int id) {
        Administor administor = administorMapper.findById(id);
        List ids = administorMapper.findRoleIdsById(id);
        System.out.println("role_ids"+ids);
        if(ids.size() == 0){
            throw new AdministorServiceException("该管理员还未分配角色！");
        }
        List<Role> roles = roleMapper.findByList(ids);
        System.out.println("roles"+roles);
        administor.setRoles(roles);
        return administor;
    }

    @Override
    public void modifyMessage(Administor administor) {
        int row = administorMapper.updateMessage(administor);
        System.out.println(administor);
        System.out.println("row:"+row);
        if(row != 1){//若影响的记录超过一行，则抛异常回滚
            throw new AdministorServiceException("数据并发出错，请稍后重试！");
    }
    }

    @Override
    public PageResult<Administor> findAll(int currentPage) {
        PageHelper.startPage(currentPage,10);
        List<Administor> administors = administorMapper.findAll();
        if(administors.size() <= 0 ){
            throw new AdministorServiceException("数据并发出错，请稍后重试！");
        }
        for(Administor administor : administors){
            List ids = administorMapper.findRoleIdsById(administor.getAdminId());
            System.out.println("role_ids"+ids);
            List<Role> roles = roleMapper.findByList(ids);
            System.out.println("roles"+roles);
            administor.setRoles(roles);
        }
        PageInfo<Administor> pageInfo = new PageInfo<>(administors);
        PageResult pageResult = new PageResult<Administor>();
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setItems(pageInfo.getList());
        return pageResult;
    }

    @Override
    public Map<String, Object> getAdministorAndRoles(int id) {
        //根据id所查询的管理员及其所拥有角色信息
        Administor administor = administorMapper.findById(id);
        List ids = administorMapper.findRoleIdsById(id);
        System.out.println("role_ids"+ids);
        if(ids.size() == 0){
            throw new AdministorServiceException("该管理员还未分配角色！");
        }
        List<Role> roles = roleMapper.findByList(ids);
        System.out.println("roles"+roles);
        administor.setRoles(roles);
        //查询所有的角色
        List<Role> roles1 = roleMapper.findAll();
        //创建map，封装单个管理员的信息和所有的角色的信息
        Map administorAndRoles = new HashMap<String, Object>();
        administorAndRoles.put("administor",administor);
        administorAndRoles.put("allRole",roles1);
        return administorAndRoles;
    }

    @Override
    public void modifiedMessage(Administor administor, List<Integer> roleIds) {
        //修改管理员信息
        int row = administorMapper.updateMessage(administor);
        if(row != 1){
            throw new AdministorServiceException("信息保存出错，请稍后重试！");
        }
        //修改管理员的角色信息
        //删除管理员的所有角色
        int administorId = administor.getAdminId();
        administorMapper.deleteAllRole(administorId);
        for(int roleId : roleIds){
            int row1 = administorMapper.updateRoleMessage(administorId, roleId);
            if(row1 !=1 ){
                throw new AdministorServiceException("信息保存出错，请稍后重试！");
            }
        }

    }

    @Override
    public void resetPassword(List<Administor> administors) {
        for(Administor administor : administors){
            String newPassword = DigestUtils.md5Hex(administor.getAdminNumber().getBytes());
            administor.setPassword(newPassword);
            int row = administorMapper.updatePassword(administor);
            if(row !=1 ){
                throw new AdministorServiceException("系统错误，请重试！");
            }
        }
    }

    @Override
    public void deleteAdministor(int id) {
        int row = administorMapper.deleteById(id);
        if(row !=1 ){
            throw new AdministorServiceException("系统错误，请重试！");
        }
    }

    @Override
    public List<Role> preAdd() {
        List<Role> roles = roleMapper.findAll();
        if(roles.size() <= 0){
            throw new RoleServiceException("当前无角色，不可添加管理员，请先添加角色！");
        }
        return roles;
    }

    @Override
    public void addAdministor(Administor administor, List<Integer> roleIds) {
        administor.setPassword(DigestUtils.md5Hex(administor.getPassword().getBytes()));//将密码加密
        int adminId = administorMapper.addAdministor(administor);
        if(adminId > 0 ){//adminId大于0表示管理员的信息已经插入到管理员表中，接下来需要插入为其分配的角色信息
            for(int roleId : roleIds){
                int row1 = administorMapper.updateRoleMessage(adminId, roleId);
                if(row1 !=1 ){
                    throw new AdministorServiceException("信息保存出错，请稍后重试！");
                }
            }
        }
    }

    @Override
    public PageResult<Administor> selectAdministorByModuleId(int currentPage, int moduleId) {
        PageHelper.startPage(currentPage,10);
        String moduleName = moduleMapper.findModuleNameById(moduleId);
        System.out.println("模块名称："+moduleName);
        List<Administor> administors = administorMapper.findAll();
        if(administors.size() <= 0 ){
            throw new AdministorServiceException("数据并发出错，请稍后重试！");
        }
        List<Administor> administorList = new ArrayList<>();
        for(Administor administor : administors){
            List ids = administorMapper.findRoleIdsById(administor.getAdminId());
            List<Role> roles = roleMapper.findByList(ids);
            for(Role role : roles){
                List<Role> rolelist = new ArrayList<>();
                for(Module module : role.getModules()){
                    if(module.getName().equals(moduleName)){
                        rolelist.add(role);
                    }
                }
                if(rolelist.size() > 0){
                    administor.setRoles(rolelist);
                    administorList.add(administor);
                }
            }
        }
        PageInfo<Administor> pageInfo = new PageInfo<>(administorList);
        PageResult pageResult = new PageResult<Administor>();
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setItems(pageInfo.getList());
        return pageResult;
    }


    @Override
    public PageResult<Administor> selectAdministorByRoleName(int currentPage, String name) {
        PageHelper.startPage(currentPage,10);
        if(name==""){
            throw new AdministorServiceException("角色名称不可以为空！");
        }
        Integer roleId = roleMapper.findRoleIdByName(name);
        if(roleId==null || roleId.equals("")){
            throw new AdministorServiceException("不存在该角色！");
        }
        List adminIds = administorMapper.findAdminIdByRoleId(roleId);
        if(adminIds.size() <= 0){
            throw new AdministorServiceException("该角色无对应的管理员");
        }
        List<Administor> administors = administorMapper.findByIds(adminIds);
        if(administors.size() <= 0 ){
            throw new AdministorServiceException("无对应数据！");
        }
        for(Administor administor : administors){
            List roleIds = administorMapper.findRoleIdsById(administor.getAdminId());
            List<Role> roles = roleMapper.findByList(roleIds);
            System.out.println("roles"+roles);
            administor.setRoles(roles);
        }
        PageInfo<Administor> pageInfo = new PageInfo<>(administors);
        PageResult pageResult = new PageResult<Administor>();
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setItems(pageInfo.getList());
        return pageResult;
    }

}
