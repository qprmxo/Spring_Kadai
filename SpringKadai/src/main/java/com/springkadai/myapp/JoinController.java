package com.springkadai.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springkadai.myapp.dao.LoginDao;

@Controller
public class JoinController {
	
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model) {
		
		
		return "";
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(Model model, String id) {
		
		return loginDao.idCheck(id);
	}

}
