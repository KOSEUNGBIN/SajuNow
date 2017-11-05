package com.landvibe.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping("")
	public ModelAndView getList() {

		ModelAndView mav = new ModelAndView("advertisement/change");

		return mav;
	}
	
	@RequestMapping(value ="/upload/{slideNo:[0-9]+}" , method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView UploadImage(@PathVariable long slideNo, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		final String filePath = "/home/oursoccer/www/study/advertisement/";
		ModelAndView mav = new ModelAndView("redirect:/ad");
		
		String storedFileName = null;
		
		
		System.out.println(file.toString());

		File file_temp = new File(filePath);
		if (file_temp.exists() == false) {
			file_temp.mkdirs(); // 筌�占쏙옙占쏙옙占쏙옙占썹㎉占쏙옙占� 占쎈��占쏙옙揶�占� 占쏙옙占쎌�겹��
							// 占쎈��占쏙옙占쏙옙占쏙옙
		}

			if (file.isEmpty() == false) {
				storedFileName = "ad_"+ slideNo  + ".jpg";
				file_temp = new File(filePath + storedFileName);
				file.transferTo(file_temp); 

			}
			else
				System.out.println("multipartFile is Empty!!");
		

			

			return mav;

	}


}
