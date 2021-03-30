package com.pxxy.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @Descricption:
 * @Author:江灿
 * @Date:Create in 10:54 2019/5/31
 */
public class TestLog {
    private static final Logger LOGGER= LogManager.getLogger(TestLog.class.getName());
    @Test
    public void test1(){
        try {
            new TestLog();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

    }
}
