package com.mindtree.WebApp;




import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hospitalDao.DatabaseConn;

@CrossOrigin
@EnableAutoConfiguration
@Controller
public class HomeController {

	@ResponseBody
	@RequestMapping(value = "/testByEmail",method= {RequestMethod.POST})
	public String Home2( String email , String test ) throws Exception {
		DatabaseConn ob=new DatabaseConn();
		ob.testDetailsByEmail( email,test);
		return "Inserted Successfully";
	}
	@ResponseBody
	@RequestMapping(value = "/testByPhone",method= {RequestMethod.POST})
	public String Home3( String phone , String test ) throws Exception {
		DatabaseConn ob=new DatabaseConn();
		ob.testDetailsByPhone(phone,test);
		return "Inserted Successfully";
	}
	@ResponseBody
	@RequestMapping(value = "/reportDetailByEmail")
	public String Home4( String email , String test ) throws Exception {
		DatabaseConn ob=new DatabaseConn();
		String reportList= "";
		reportList=ob.report(email ,test,"e");
		System.out.println(reportList);
		return reportList;
	}
	@ResponseBody
	@RequestMapping(value = "/reportDetailByPhone")
	public String Home5( String phone , String test ) throws Exception {
		DatabaseConn ob=new DatabaseConn();
		String reportList="";
		reportList = ob.report(phone,test,"p");
		System.out.println(reportList);
		return reportList;
	}
}


