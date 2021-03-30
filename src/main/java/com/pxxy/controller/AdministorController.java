package com.pxxy.controller;

import com.github.pagehelper.PageInfo;
import com.pxxy.domain.*;
import com.pxxy.mapper.AdministorMapper;
import com.pxxy.service.IAdministorService;
import com.pxxy.service.ex.AdministorServiceException;
import com.pxxy.service.ex.RoleServiceException;
import com.pxxy.service.impl.AdministorService;
import com.pxxy.utils.ImgCheckCode;
import net.sf.json.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descricption:管理员表现层
 * @Author:江灿
 * @Date:Create in 11:12 2019/5/29
 */
@Controller
@RequestMapping("/administor")
public class AdministorController {

    @Autowired
    private IAdministorService administorService;

   /*
    * @Author:江灿
    * @Description:获取验证码
    * @Date: 15:49 2019/5/30
    * @Param: []
    * @return: java.lang.String
    **/
   @RequestMapping("/getImgCode")
    public void getImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
       ImgCheckCode imgCheckCode = new ImgCheckCode();
       String code = imgCheckCode.getRandomCodeStr();
       BufferedImage buffImg = imgCheckCode.getImgCode(code);
       System.out.println("Code is " + code);
       // 将四位数字的验证码保存到Session中。
       HttpSession session = request.getSession();
       session.setAttribute("code", code);
       // 禁止图像缓存。
       response.setHeader("Pragma", "no-cache");
       response.setHeader("Cache-Control", "no-cache");
       response.setDateHeader("Expires", 0);
       response.setContentType("image/jpeg");
       // 将图像输出到Servlet输出流中。
       ServletOutputStream sos = response.getOutputStream();
       ImageIO.write(buffImg, "jpeg", sos);
       sos.close();
   }
    /**
     * 检查提交的验证码是否正确
     */
    @RequestMapping(value ="/checkCode")
    @ResponseBody//使用@ResponseBody 注解返回响应体 直接将返回值序列化json
    public ResponseResult<Void> checkCode(HttpServletRequest request , String code){
        HttpSession session = request.getSession();
        if(code.equals(session.getAttribute("code").toString())){
            //如果验证码正确，则给前台发送成功消息，让前台请求登录
            return new ResponseResult<Void>(0);
        }else{
            return new ResponseResult<Void>(1,"验证码不正确，请重新输入！");
        }
    }
    /*
     * @Author:江灿
     * @Description:管理员登录
     * @Date: 17:05 2019/5/30
     * @Param: [administor]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> login(Administor administor,HttpSession session){
        try {
            Administor administor1 = administorService.login(administor);
            //登录成功，将管理员信息保存到session中,给前台发送请求，让其请求主页
            session.setAttribute("administor",administor1);
            System.out.println("登录成功");
            return new ResponseResult<>(0);
        } catch (AdministorServiceException e) {
            System.out.println("登录失败");
            System.out.println(e.getMessage());
            return new ResponseResult<>(1,e.getMessage());
        }
    }
    /**
     * @Author:江灿
     * @Description:管理员登录成功，请求主页
     * @Date: 8:54 2019/6/3
     * @Param: [session]
     * @return: com.pxxy.domain.ResponseResult<java.awt.List>
     **/
    @RequestMapping("/index")
    @ResponseBody
    public ResponseResult<List> index(HttpSession session){
        //从session中拿到已登录成功的管理员信息
        //Administor administor = (Administor) session.getAttribute("administor");
        //模仿已登录的管理员是超级管理员
        Administor administor = new Administor();
        administor.setAdminId(1);
        administor.setAdminNumber("superAdmin");
        administor.setName("超级管理员");
        administor.setTelephone("15879238875");
        administor.setEmail("1101985696@qq.com");
        administor.setCreateTime(new Date(2019-05-29));

        List authority = null;
        try {
            //根据管理员的id来查询其所拥有的权限
            authority = administorService.findAuthorityById(administor.getAdminId());
        } catch (AdministorServiceException e) {
            return new ResponseResult<List>(1,e.getMessage());
        }
        return new ResponseResult<List>(0,"",authority);
    }

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ResponseResult<Void> modifyPassword(String oldPassword,String newPassword,String repeatPassword){
        //从session中拿到已登录成功的管理员信息
        //Administor administor = (Administor) session.getAttribute("administor");
        //模仿已登录的管理员是超级管理员
        Administor administor = new Administor();
        administor.setAdminId(1);
        administor.setAdminNumber("superAdmin");

        //校验旧密码
        Boolean flag = administorService.checkOldPassword(administor.getAdminId(),oldPassword);
        if(flag){//flag为true，则先进行两次新密码比对，通过就修改密码，否则抛出两次密码不一致异常
            if(newPassword.equals(repeatPassword)){//两次密码一致，修改密码
                try {
                    administor.setPassword(newPassword);
                    administorService.updatePassword(administor);
                    return new ResponseResult<>(0,"密码修改成功！");
                } catch (AdministorServiceException e) {
                   return new ResponseResult<>(1,e.getMessage());
                }
            }else {//两次密码输入不一致
                return new ResponseResult<>(1,"两次密码输入不一致！");
            }
        }else {//旧密码错误
            return new ResponseResult<>(1,"旧密码错误，请重新输入！");
        }
    }

    /**
     * @Author:江灿
     * @Description:返回管理员信息
     * @Date: 16:15 2019/6/3
     * @Param: [session]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/attainMessage")
    @ResponseBody
    public ResponseResult<Administor> attainMessage(HttpSession session){
        //从session中拿到已登录成功的管理员信息
        //Administor administor = (Administor) session.getAttribute("administor");
        //模仿已登录的管理员是超级管理员
        Administor administor = new Administor();
        administor.setAdminId(1);
        administor.setAdminNumber("superAdmin");

        try {
            Administor administor1 = administorService.selectAdministor(administor.getAdminId());
            System.out.println(administor1);
            return new ResponseResult<Administor>(0,"",administor1);
        } catch (AdministorServiceException e) {
            return new ResponseResult<Administor>(1,e.getMessage(),administor);
        }
    }

    /**
     * @Author:江灿
     * @Description:修改管理员信息
     * @Date: 10:11 2019/6/4
     * @Param: [administor]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/modifyMessage")
    @ResponseBody
    public ResponseResult<Void> modifyMessage(Administor administor){
        //从session中拿到已登录成功的管理员信息
        //Administor administor1 = (Administor) session.getAttribute("administor");
        //模仿已登录的管理员是超级管理员
        Administor administor1 = new Administor();
        administor1.setAdminId(1);
        administor1.setAdminNumber("superAdmin");

        administor.setAdminId(administor1.getAdminId());
        try {
            administorService.modifyMessage(administor);
            return new ResponseResult<>(0,"保存成功！");
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

   /**
    * @Author:江灿
    * @Description:进入管理员模块，呈现所有管理员信息
    * @Date: 14:39 2019/6/4
    * @Param: [currentPage]
    * @return: com.pxxy.domain.ResponseResult<com.pxxy.domain.PageResult>
    **/
    @RequestMapping("/getAllAdministor")
    @ResponseBody
    public ResponseResult<PageResult> getAllAdministor(Integer currentPage){
        if(currentPage==null){//首次访问时，直接定位在第一页
            currentPage = 1;
        }
        try {
            PageResult<Administor> pageResult = administorService.findAll(currentPage);
            return new ResponseResult<>(0,"",pageResult);
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:获取即将信息被修改的详细信息，以及所有角色信息
     * @Date: 16:09 2019/6/4
     * @Param: [adminId]
     * @return: com.pxxy.domain.ResponseResult<java.util.Map>
     **/
    @RequestMapping("/administorGetAdministorMessage")
    @ResponseBody
    public ResponseResult<Map> administorGetAdministorMessage(int adminId){
        Map administorAndRoles = administorService.getAdministorAndRoles(adminId);
        return new ResponseResult<>(0,"",administorAndRoles);
    }

    /**
     * @Author:江灿
     * @Description:具有管理员权限的管理员修改其他管理员信息
     * 备注：该方法未测试
     * @Date: 16:54 2019/6/4
     * @Param: [administor, roleIds]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping("/modifiedMessage")
    @ResponseBody
    public ResponseResult<Void> modifiedMessage(@RequestParam(value = "administor")Administor administor, @RequestParam(value = "roleIds")List<Integer> roleIds){
        try {
            administorService.modifiedMessage(administor, roleIds);
            return new ResponseResult<>(0,"保存成功！");
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:为勾选的管理员重置密码
     * @Date: 17:05 2019/6/4
     * @Param: [administors]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @PostMapping("/resetPassword")
    @ResponseBody
    public ResponseResult<Void> resetPassword(@RequestBody List<Administor> administors){
        System.out.println(administors);
        try {
            administorService.resetPassword(administors);
            return new ResponseResult<>(0,"重置成功！");
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:删除管理员
     * @Date: 11:06 2019/6/6
     * @Param: [adminId]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @RequestMapping(value = "/deleteAdministor")
    @ResponseBody
    public ResponseResult<Void> deleteAdministor(int adminId){
        System.out.println("删除了一个管理员！");
        try {
            administorService.deleteAdministor(adminId);
            return new ResponseResult<>(0,"删除成功！");
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:进入添加管员界面，提供所有角色信息
     * @Date: 15:39 2019/6/5
     * @Param: []
     * @return: com.pxxy.domain.ResponseResult<java.util.List>
     **/
    @RequestMapping(value = "/preAdd")
    @ResponseBody
    public ResponseResult<List> preAdd(){
        try {
            //获取所有角色信息
            List<Role> roles = administorService.preAdd();
            return new ResponseResult<>(0,"",roles);
        }catch (RoleServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

    /**
     * @Author:江灿
     * @Description:添加管理员
     * @Date: 9:13 2019/6/6
     * @Param: [administor, repeatPassword, roleIds]
     * @return: com.pxxy.domain.ResponseResult<java.lang.Void>
     **/
    @PostMapping(value = "/addAdministor")
    @ResponseBody
    public ResponseResult<Void> addAdministor(Administor administor, String repeatPassword, List<Integer> roleIds) {
        if (administor.getName().isEmpty() && administor.getName() == "") {
            return new ResponseResult<>(1, "姓名不能为空！");
        }
        if (administor.getAdminNumber().isEmpty() && administor.getAdminNumber() == "") {
            return new ResponseResult<>(1, "管理员账号不能为空！");
        }
        if (administor.getPassword().isEmpty() && administor.getPassword() == "") {
            return new ResponseResult<>(1, "密码不能为空！");
        }
        if (repeatPassword.isEmpty() && repeatPassword == "") {
            return new ResponseResult<>(1, "密码不能为空！");
        }
        if (!administor.getPassword().equals(repeatPassword)) {
            return new ResponseResult<>(1, "两次密码不一致！");
        }
        if (administor.getTelephone().isEmpty() && administor.getTelephone() == "") {
            return new ResponseResult<>(1, "电话号码不能为空！");
        }
        if (administor.getEmail().isEmpty() && administor.getEmail() == "") {
            return new ResponseResult<>(1, "邮箱不能为空！");
        }
        if (roleIds.size() <= 0) {
            return new ResponseResult<>(1, "至少要选择一个角色！");
        }
        try {
            administorService.addAdministor(administor, roleIds);
            return new ResponseResult<>(0, "保存成功！");
        } catch (RoleServiceException e) {
            return new ResponseResult<>(1, e.getMessage());
        }
    }
    /*
     * @Author:江灿
     * @Description:按模块功能筛选管理员
     * @Date: 14:52 2019/6/6
     * @Param: [currentPage, moduleId]
     * @return: com.pxxy.domain.ResponseResult<com.pxxy.domain.PageResult>
     **/
    @RequestMapping("/selectAdministorByModuleId")
    @ResponseBody
    public ResponseResult<PageResult> selectAdministorByModuleId(Integer currentPage, Integer moduleId){
        if(currentPage==null){//首次访问时，直接定位在第一页
            currentPage = 1;
        }
        try {
            PageResult<Administor> pageResult = administorService.selectAdministorByModuleId(currentPage,moduleId);
            return new ResponseResult<>(0,"",pageResult);
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }
    /*
     * @Author:江灿
     * @Description:按角色名称筛选管理员
     * @Date: 14:54 2019/6/6
     * @Param: [currentPage, name]
     * @return: com.pxxy.domain.ResponseResult<com.pxxy.domain.PageResult>
     **/
    @RequestMapping("/selectAdministorByRoleName")
    @ResponseBody
    public ResponseResult<PageResult> selectAdministorByModuleId(Integer currentPage, String name){
        if(currentPage==null){//首次访问时，直接定位在第一页
            currentPage = 1;
        }
        try {
            PageResult<Administor> pageResult = administorService.selectAdministorByRoleName(currentPage,name);
            return new ResponseResult<>(0,"",pageResult);
        }catch (AdministorServiceException e){
            return new ResponseResult<>(1,e.getMessage());
        }
    }

}
