package com.example.demo1.service;

public interface MailService {
    public void sendTemplateMail(String to, String subject, String content);
    public void sendSimpleMail(String to, String subject, String content);
    public void sendHtmlMail(String to, String subject, String content);
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
    public void sendInlineResourceMail(String to, String subject, String content,String rscPath, String rscId);
}
