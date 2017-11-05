package com.landvibe.api;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("ad")
public class AdvertisementController {


	
	@RequestMapping("/image/{adNo:[0-9]+}")
	@ResponseBody
	public HttpEntity<byte[]> getPhoto(@PathVariable Long adNo) {
		byte[] image = null;
		try {
			image = org.apache.commons.io.FileUtils
					.readFileToByteArray(new File("/home/oursoccer/www/study/advertisement/ad_" + adNo + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				image = org.apache.commons.io.FileUtils
						.readFileToByteArray(new File("/home/oursoccer/www/company/" + "profile_default" + ".jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new HttpEntity<byte[]>(image, headers);
	}
	
	


}
