package com.mindtree.WebApp;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestHomeController {

	
	@ResponseBody
	@RequestMapping(value = "/")
	public String phone(@RequestParam String phone, @RequestParam String email, @RequestParam String Date)
	{
		
		//System.out.println("\n AMit \n");
		return phone+" "+email+" "+Date;
	}
	
}
