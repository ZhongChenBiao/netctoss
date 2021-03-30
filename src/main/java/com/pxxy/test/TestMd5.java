package com.pxxy.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

/**
 * @Descricption:
 * @Author:江灿
 * @Date:Create in 17:48 2019/5/30
 */
public class TestMd5 {
    @Test
    public void testMd5(){
        String s1 = DigestUtils.md5Hex("superAdmin".getBytes());
        System.out.println(s1);
    }
}
