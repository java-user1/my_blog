package com.lk.my_blog.service;

/**
 * @Author: 刘康
 * @Date: 2021/7/29 12:28
 * @Description: 邮件发送功能
 */
public interface MailService {

    /**
     * 简单邮件发送
     * @param to 接受者
     * @param subject 主题
     * @param context 内容
     */
    void sendSimpleMail(String to,String subject,String context,String from);

    /**
     * 附件邮件
     * @param to 接收者
     * @param subject 主题
     * @param context 内容
     * @param filePath 附件路径
     */
    void sendAttachmentsMail(String to, String subject, String context, String filePath);

    /**
     * 图片邮件
     * @param to 接收者
     * @param subject 主题
     * @param context 内容
     * @param rscPath 图片路径
     * @param rscId 图片ID
     */
    void sendImageMail(String to, String subject, String context, String rscPath, String rscId);
}
