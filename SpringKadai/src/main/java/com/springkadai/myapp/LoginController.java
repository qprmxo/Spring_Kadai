package com.springkadai.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springkadai.myapp.dao.LoginDao;

@Controller
public class LoginController {
	
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String id, String pass) {

		String login = loginDao.login(id, pass);
		if("true".equals(login)) {
			return "list";
		}else {
			model.addAttribute("result",login);
			return "result";
		}
	}
	
}
