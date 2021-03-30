package com.pxxy.test;



import com.pxxy.mapper.AccountMapper;
import com.pxxy.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestDao {
    @Test
    public void testDao()throws Exception{
        InputStream in =  Resources.getResourceAsStream("config/applicationContext.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        List<Account> accounts = accountMapper.findAll();
        for (Account account : accounts){
            System.out.println(account);
        }
    }


}
