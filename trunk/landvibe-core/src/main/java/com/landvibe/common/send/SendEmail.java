package com.landvibe.common.send;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	 public void sendNaverEmail(String email) throws MessagingException{
		 	// 네이버 메일 서버 설정
	        Properties props = System.getProperties();
	   
	        props.put("mail.smtp.host", "smtp.naver.com");		// 네이버 메일 서버 - host
	        props.put("mail.smtp.port", 465);					// 포트 번호
	        props.put("mail.smtp.auth", "true");				// 권한?
	        props.put("mail.smtp.ssl.enable", "true");			// security?
	        props.put("mail.smtp.ssl.trust", "smtp.naver.com");	// host
	           
	        // 네이버 메일 서버에 대한 인증 정보 설정
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        	protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("Email Id", "Email Password");
	            }
	        });
	        
	        //for debug
	        session.setDebug(true); 
	        
	        // 메일에 보낼 메시지
	        String test ="History Error!!";
	        // 메일 설정
	        // 네이버 메일 서버에 대한 인증 정보를 담는다.
	        Message mimeMessage = new MimeMessage(session);
	       
	        mimeMessage.setFrom(new InternetAddress("{Email Id}@naver.com"));	// 메일 발신자 (사주나우 관리자)
	        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 메일 수신자 
	        mimeMessage.setSubject("사주나우 인증 서비스");		// 메일 제목
//	        mimeMessage.setText(body);
	        mimeMessage.setContent(test,"text/html; charset=utf-8");	// 메일 내용
	        
	        Transport.send(mimeMessage);	// 메일 발송
	        
	    }
}
