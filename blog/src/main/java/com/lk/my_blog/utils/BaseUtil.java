package com.lk.my_blog.utils;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.lk.my_blog.config.LogConfig;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Objects;

/**
 * @Author: 刘康
 * @Date: 2021/8/14 15:02
 * @Description: 基础工具类
 * */

public class BaseUtil {
    @Autowired
    private static LogConfig logConfig;

    private Image img;
    /**
     * base64格式图片保存
     * @param base64Str base64字符串
     * @param imgFilePath 保存路径
     * @return
     */
    public static boolean generateImage(String base64Str, String imgFilePath) {
        if(base64Str == null){
            return false;
        }
        String[] split = base64Str.split(",");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(split[1]);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            String path = ResourceUtils.getURL("blog\\src\\main\\resources\\static"+imgFilePath).getPath();
            // 生成jpeg图片
            File file = new File(path);
            String path1 = file.getAbsolutePath();
            OutputStream out = new FileOutputStream(path1);
            out.write(bytes);
            out.flush();
            out.close();
            //====
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * markdown整合图片文件上传
     * @param filecontent
     */
    public static void approvalFile( MultipartFile filecontent){

        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = filecontent.getInputStream();
            fileName = filecontent.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = System.getProperty("user.dir")+"\\blog\\src\\main\\resources\\static\\admin\\images\\content_image\\"
                    +fileName;
            // 2K的数据缓冲
            byte[] bs = new byte[2048];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File file = new File(path);
            // 如果图片已经存在，不再重复保存
            String path1 = file.getAbsolutePath();
            os = new FileOutputStream(path1);
            OutputStream outputStream = resizeImage(inputStream, os, 500, "jpg");
            // 开始读取
            while ((len = inputStream != null ? inputStream.read(bs) : 0) != -1) {
                outputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static OutputStream resizeImage(InputStream is, OutputStream os, int size, String format)
            throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size/width;
        int newWidth = (int)(width * percent);
        int newHeight = (int)(height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0,newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        return os;
    }



}
