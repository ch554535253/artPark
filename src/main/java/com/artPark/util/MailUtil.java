package com.artPark.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Author lbc on 2020/12/11  11:41.
 */
public class MailUtil {
    private final static String HOST;
    private final static String PORT;
    private final static String MAIL_ACCOUNT;
    private final static String MAIL_PASSWORD;
    private final static String ACCOUNT_NAME;
    private final static Properties props = new Properties();
    private static Session session;
    private final static String CHARSET = "UTF-8";

    static {
        HOST = "smtp.qq.com";
        PORT = "";
        MAIL_ACCOUNT = "554535253@qq.com";
        MAIL_PASSWORD = "vrlvcmurkrpbbbjh";
        ACCOUNT_NAME = "低保大爷";
        initMailConfig();
    }

    public static void initMailConfig(){
        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol","smtp");
            props.setProperty("mail.smtp.host",HOST);
            props.setProperty("mail.smtp.auth","true");

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
            /*
            // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
            //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
            //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
            */
            session = Session.getInstance(props);
            session.setDebug(true);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void sendMail(String title, String content, List<String> receivers) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(MAIL_ACCOUNT, ACCOUNT_NAME, CHARSET));
        for (String receiver: receivers){
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver,receiver,CHARSET));
        }
        msg.setSubject(title,CHARSET);
        msg.setContent(content,"text/html;charset=UTF-8");
        msg.setSentDate(new Date());
        msg.saveChanges();
        Transport transport = session.getTransport();
        transport.connect(MAIL_ACCOUNT, MAIL_PASSWORD);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) {
//        try{
//            String title = "艺术长廊注册验证";
//            String content = "您的验证码为：0000";
//            sendMail(title,content, Collections.singletonList("realjoechen@icloud.com"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Son son = new Son();
        System.out.println("------end------");
    }

    public static void aaa(String[] args) throws Exception {
        // 1. 创建一封邮件
        Properties props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        Session session= Session.getInstance(props);        // 根据参数配置，创建会话对象（为了发送邮件准备的）
        MimeMessage message = new MimeMessage(session);     // 创建邮件对象

        /*
         * 也可以根据已有的eml邮件文件创建 MimeMessage 对象
         * MimeMessage message = new MimeMessage(session, new FileInputStream("myEmail.eml"));
         */

        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress("554535253@qq.com", "USER_AA", "UTF-8"));

        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("cc@receive.com", "USER_CC", "UTF-8"));
        //    To: 增加收件人（可选）
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
        //    Cc: 抄送（可选）
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
        //    Bcc: 密送（可选）
        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("邮件主题", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("这是邮件正文", "text/html;charset=UTF-8");

        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 7. 保存前面的设置
        message.saveChanges();

        // 8. 将该邮件保存到本地
        OutputStream out = new FileOutputStream("C:/Users/jeff/Desktop/myEmail.eml");
        message.writeTo(out);
        out.flush();
        out.close();
    }

}
