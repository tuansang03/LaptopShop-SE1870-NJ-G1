package model;

import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import model.MailConfig;

public class SendMailSSL {

    public void sendOTP(String recipientEmail, String otp) {
        // Lấy thuộc tính cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);

        // Khởi tạo phiên làm việc (Session)
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });

        // Tạo nội dung email (compose message)
        try {
            MimeMessage message = new MimeMessage(session);
            // Thay thế MailConfig.RECEIVE_EMAIL bằng recipientEmail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("OTP Confirmation");

            // HTML content for the email
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f0f4f8;'>"
                + "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                + "<div style='background-color: #003366; color: white; padding: 10px 20px; border-top-left-radius: 8px; border-top-right-radius: 8px;'>"
                + "<h2 style='margin: 0;'>OTP Confirmation</h2>"
                + "</div>"
                + "<div style='padding: 20px;'>"
                + "<p style='font-size: 16px;'>Dear customer,</p>"
                + "<p style='font-size: 16px;'>Thank you for registering with us. Please use the following OTP to complete your registration:</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #003366; text-align: center;'>" + otp + "</p>"
                + "</div>"
                + "<div style='background-color: #e0e0e0; padding: 10px; text-align: center; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;'>"
                + "<p style='font-size: 14px; color: #555;'>If you did not request this OTP, please ignore this email.</p>"
                + "</div>"
                + "</div>"
                + "</div>";

            // Set the HTML content in the email
            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);

            System.out.println("Message sent successfully with HTML content");
        } catch (MessagingException e) {
            e.printStackTrace(); // Debug lỗi
            throw new RuntimeException(e);
        }
    }
 public void sendNewPassword(String recipientEmail, String newPassword) {
        // Lấy thuộc tính cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);

        // Khởi tạo phiên làm việc (Session)
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });

        // Tạo nội dung email (compose message)
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your New Password");

            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f0f4f8;'>"
                + "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                + "<div style='background-color: #003366; color: white; padding: 10px 20px; border-top-left-radius: 8px; border-top-right-radius: 8px;'>"
                + "<h2 style='margin: 0;'>Password Reset</h2>"
                + "</div>"
                + "<div style='padding: 20px;'>"
                + "<p style='font-size: 16px;'>Dear customer,</p>"
                + "<p style='font-size: 16px;'>Your password has been reset. Please use the following new password to log in:</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #003366; text-align: center;'>" + newPassword + "</p>"
                + "<p style='font-size: 16px;'>For security reasons, we recommend you change your password after logging in.</p>"
                + "</div>"
                + "<div style='background-color: #e0e0e0; padding: 10px; text-align: center; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;'>"
                + "<p style='font-size: 14px; color: #555;'>If you did not request a password reset, please ignore this email.</p>"
                + "</div>"
                + "</div>"
                + "</div>";

            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);

            System.out.println("Password reset email sent successfully with HTML content");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
 public void send(String to, String subject, String mess,HttpServletRequest request) throws UnsupportedEncodingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", MailConfig.HOST_NAME);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", MailConfig.TSL_PORT);

    // get Session
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
        }
    });

    // compose message
    try {
        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setFrom(new InternetAddress(MailConfig.APP_EMAIL, "Laptop Shop", "UTF-8"));

        // Nội dung HTML với CSS đã cải tiến
     String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f4f6f8; border-radius: 10px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);'>"
                + "<div style='background-color: #003366; color: white; padding: 15px; text-align: center; border-radius: 10px 10px 0 0;'>"
                + "<h1 style='margin: 0; font-size: 24px;'>Laptop Shop</h1>"
                + "</div>"
                + "<div style='background-color: #ffffff; padding: 20px; border-bottom: 2px solid #003366;'>"
                + "<p style='font-size: 16px; color: #333; line-height: 1.6;'>Dear Customer,</p>"
                + "<p style='font-size: 16px; color: #333; line-height: 1.6;'>"
                + "To reset your password, please click the following link: "
                + "<a href='" + mess + "' style='color: #003366; text-decoration: none;'>Reset Password</a>"
                + "</p>"
                + "</div>"
                + "<div style='background-color: #f0f0f0; padding: 15px; text-align: center; border-radius: 0 0 10px 10px;'>"
                + "<p style='font-size: 14px; color: #666;'>If you did not request this, please ignore this email.</p>"
                + "<p style='font-size: 14px; color: #666;'>Laptop Shop © 2024. All rights reserved.</p>"
                + "</div>"
                + "</div>";



        // Set the HTML content to the message
        message.setContent(htmlContent, "text/html; charset=UTF-8");

        // Send message
        Transport.send(message);

        System.out.println("Message sent successfully with HTML content");
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}





    public static void main(String[] args) {

        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);

        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailConfig.RECEIVE_EMAIL));
            message.setSubject("OTP Confirmation");
            message.setText("Your OTP is");

            // send message
            Transport.send(message);

            System.out.println("OTP sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
