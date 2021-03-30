package com.pxxy.mapper;

import com.pxxy.domain.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Descricption:权限的持久层接口
 * @Author:江灿
 * @Date:Create in 11:21 2019/5/29
 */
@Repository()
public interface ModuleMapper {

    /**
     * @Author:江灿
     * @Description:根据id集合查询
     * @Date: 10:10 2019/6/6
     * @Param: [moduleIds]
     * @return: java.util.List<com.pxxy.domain.Module>
     **/
   List<Module> findAllModule(List moduleIds);

   /*
    * @Author:江灿
    * @Description:查询模块名称
    * @Date: 17:15 2019/6/6
    * @Param: []
    * @return: java.lang.String
    **/
   String findModuleNameById(int moduleId);
}
