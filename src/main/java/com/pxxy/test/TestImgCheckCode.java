package com.pxxy.test;

import com.pxxy.utils.ImgCheckCode;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Descricption:
 * @Author:江灿
 * @Date:Create in 15:20 2019/5/30
 */
public class TestImgCheckCode {
    @Test
    public void  testImgCheckCode(){
        ImgCheckCode imgCheckCode=new ImgCheckCode();
        String code=imgCheckCode.getRandomCodeStr();
        BufferedImage buffImg= imgCheckCode.getImgCode(code);
        System.out.println("Code is " +code);
    }
}
