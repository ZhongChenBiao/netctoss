package com.pxxy.test;

import com.pxxy.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 业务层的测试类
 */
public class TestService {
    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        IAccountService  as = ac.getBean("accountService",IAccountService.class);
        as.findAll();
    }
}
