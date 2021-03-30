package com.pxxy.controller;

import com.github.pagehelper.PageInfo;
import com.pxxy.domain.Account;
import com.pxxy.domain.PageBean;
import com.pxxy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

/**
 * 账户的表现层
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    /**
     * 查询所有
     */
    @RequestMapping("/findAll")
    public String findAll(){
        List<Account> accounts = accountService.findAll();
        for (Account account : accounts){
            System.out.println(account);
        }
        return "list";
    }

    /**
     * 按页码查询
     */
    @RequestMapping("/findByPage")
    public String findByPage(int currentPage){
        PageBean<Account> accounts = accountService.findByPage(currentPage);
        for (Account account : accounts.getLists()){
            System.out.println(account);
        }
        return "success";
    }
    /**
     * 按页码查询,使用分页助手
     */
    @RequestMapping("/findByPageHelper")
    public String findByPageHelper(int currentPage){
        PageInfo<Account> accounts = accountService.findByPageHelper(currentPage,10);
        System.out.println("分页助手");
        for (Account account : accounts.getList()){
            System.out.println(account);
        }
        return "success";
    }

}
