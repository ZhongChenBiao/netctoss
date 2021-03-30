package com.pxxy.mapper;

import com.pxxy.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Descricption:账户的持久层接口
 * @Author:江灿
 * @Date:Create in 11:19 2019/5/29
 */
@Repository()
public interface AccountMapper {
    /**
     * 查询所有
     */
//    @Select("select * from account")
    List<Account> findAll();

    /**
     * 保存账户
     */
    @Insert("insert into account(name,money) values (#{name},#{money})")
    int saveAccount(Account account);
    /**
     * 更新账户
     */
    int updateAccount(Account account);
    /**
     * 查询帐户记录总数
     */
    int selectCount();
    /**
     * 根据分页数据start 和size查询数据
     * @param map
     * @return
     */
    List<Account> findByPage(HashMap<String,Object> map);
    /**
     * 根据分页分页助手查询数据
     * @param
     * @return
     */
    List<Account> selectByPageAndSelections();
}
