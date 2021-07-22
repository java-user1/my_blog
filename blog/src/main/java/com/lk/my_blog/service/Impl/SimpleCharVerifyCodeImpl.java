package com.lk.my_blog.service.Impl;

import com.lk.my_blog.model.VerifyCode;
import com.lk.my_blog.service.VerifyCodeService;
import com.lk.my_blog.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Author: 刘康
 * @Date: 2021/6/6 10:31
 * @Description:
 */
@Service
public class SimpleCharVerifyCodeImpl implements VerifyCodeService {
    private static final String[] FONT_TYPES={"u5b8bu4f53","u65b0u5b8bu4f53", "u9ed1u4f53", "u6977u4f53", "u96b6u4e66"};
    private static final int VALICATE_CODE_LENGTH=4;

    /**
     * 设置背景颜色及大小，干扰线
     * @param graphics
     * @param width
     * @param height
     */
    private static void fillBackground(Graphics graphics,int width,int height){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,width,height);
        for(int i=0;i<8;i++){
            graphics.setColor(RandomUtils.randomColor(40,150));
            Random random = new Random();
            int x = RandomUtils.nextInt(width);
            int y = RandomUtils.nextInt(height);
            int x1=RandomUtils.nextInt(width);
            int y1=RandomUtils.nextInt(height);
            graphics.drawLine(x,y,x1,y1);
        }
    }

    /**
     * 生成随机字符
     * @param width
     * @param height
     * @param os
     * @return
     * @throws IOException
     */
    @Override
    public String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics=image.getGraphics();
        fillBackground(graphics,width,height);
        String randomStr = RandomUtils.randomString(VALICATE_CODE_LENGTH);
        createCharacter(graphics,randomStr);
        graphics.dispose();
        ImageIO.write(image,"JPEG",os);
        return randomStr;
    }

    /**
     * 验证码生成
     * @param width
     * @param height
     * @return
     */
    @Override
    public VerifyCode generate(int width, int height) {
        VerifyCode verifyCode = null;
        String code = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            verifyCode = new VerifyCode();
            code = generate(width,height,baos);
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (IOException e) {
            verifyCode=null;
        }
        return verifyCode;
    }

    /**
     * 设置字体大小及颜色
     * @param g
     * @param randomStr
     */
    public void createCharacter(Graphics g,String randomStr){
        char[] charArray = randomStr.toCharArray();
        for(int i=0;i<charArray.length;i++){
            g.setColor(new Color(50+RandomUtils.nextInt(100),
                    RandomUtils.nextInt(100),50+RandomUtils.nextInt(100)));
            g.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)],Font.BOLD,26));
            g.drawString(String.valueOf(charArray[i]),15*i+5,19+RandomUtils.nextInt(8));
        }
    }
}
