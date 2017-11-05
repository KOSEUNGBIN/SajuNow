package com.landvibe.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.Company;
import com.landvibe.core.pay.Payment;
import com.landvibe.core.pay.PaymentBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

@Controller
@RequestMapping("/pay")
@SessionAttributes("user")
public class PayController {
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private PaymentBo paymentBo;

	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
	@ResponseBody
	public Object redirect(@ModelAttribute Payment payment) throws IOException { // 인증에 성공
		
		String input = payment.getReplycode()+ payment.getTid() +payment.getMb_serial_no() + payment.getUnitprice() + "KRW";
		
		 String ah256Str = "";
         MessageDigest msgDigest = null;
         byte[] bytes = null;
         
         try {
             msgDigest = MessageDigest.getInstance("SHA-256");
             msgDigest.reset();
             msgDigest.update("skdnfoq!".getBytes());
           } catch (NoSuchAlgorithmException e) {
             throw new IllegalStateException("System doesn't support SHA-256 algorithm.");
           }
         
         try {
             bytes = msgDigest.digest(input.getBytes("UTF-8"));
           } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
           }
        
         ah256Str = new String( Hex.encodeHex(bytes));
         
		
         System.out.println("input : "+input);
		System.out.println("encrypt data : "+ah256Str);
		System.out.println("payment.getHashresult() : "+payment.getHashresult());
		if(ah256Str.equals(payment.getHashresult())) //해쉬값 일치 (검증 성공)
		{
			System.out.println("HASH-RESULT 검증 성공");
			StringBuilder urlSb = new StringBuilder();
			urlSb.append("https://service.paygate.net/djemals/settle/verifyReceived.jsp?tid=");
			urlSb.append(payment.getTid());
			urlSb.append("&verifyNum=100");
			 CloseableHttpClient httpclient = HttpClients.createDefault();
			 try {
				 HttpGet httpget = new HttpGet(urlSb.toString());
				 CloseableHttpResponse response = httpclient.execute(httpget);
				 
			     try {
		                System.out.println("----------------------------------------");
		                System.out.println(response.getStatusLine().getStatusCode());

		                // Get hold of the response entity
		                HttpEntity entity = response.getEntity();

		                // If the response does not enclose an entity, there is no need
		                // to bother about connection release
		                if (entity != null) {
		                    InputStream instream = entity.getContent();
		                    try {
		                        instream.read();
		                        if(200 == response.getStatusLine().getStatusCode())
		                        {
		                        	System.out.println("PayGate verifyReceived Call - OK!!");
		                        	Payment payment_insert = new Payment(payment.getUnitprice(), payment.getReplycode(), payment.getTid(), payment.getCardtype(), payment.getReceipttoemail());
		                        	paymentBo.create(payment_insert);
		                        	// pay insert
		                        	// 앱으로 성공 전달..
		                        	ModelAndView mav = new ModelAndView("redirect:/pay/success");
		                        	mav.addObject("payment",payment);
		                    		return mav;
		                        }
		                        else
		                        {
		                        	System.out.println("PayGate verifyReceived Call - FAIL!!");
		                        	System.out.println(response.getStatusLine().getStatusCode());
		                        }
		                        // do something useful with the response
		                    } catch (IOException ex) {
		                        // In case of an IOException the connection will be released
		                        // back to the connection manager automatically
		                    	System.out.println("PayGate verifyReceived Call - FAIL!! error : " + ex);
		                        throw ex;
		                    } finally {
		                        // Closing the input stream will trigger connection release
		                        instream.close();
		                    }
		                }
		            } finally {
		                response.close();
		            }
		        } finally {
		            httpclient.close();
		        }
		}
		ModelAndView mav = new ModelAndView("redirect:/pay/fail");
		return mav;
	}
	
	@RequestMapping("/post")
	@ResponseBody
	public Object post(HttpSession session, Company company) {
		
		String companyName = company.getNick_name();
		
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("pay/post");
		mv.addObject("user", userBo.getByNo(user.getUser_no()));
		mv.addObject("company_name", companyName );
		return mv;
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	@ResponseBody
	public Object success(@ModelAttribute Payment payment){ // 인증에 성공
		
		ModelAndView mav = new ModelAndView("/pay/success");
		mav.addObject("payment",payment);
		return mav;
	}
	
	@RequestMapping(value = "/fail", method = RequestMethod.POST)
	@ResponseBody
	public Object fail(@ModelAttribute Payment payment){ // 인증에 실패
		
		ModelAndView mav = new ModelAndView("/pay/fail");
		mav.addObject("payment",payment);
		return mav;
	}
	
}