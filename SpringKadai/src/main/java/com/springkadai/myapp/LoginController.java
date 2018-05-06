package com.springkadai.myapp;

import javax.servlet.http.HttpSession;

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
	public String login(Model model, String id, String pass, HttpSession session) {

		String login = loginDao.login(id, pass);
		if("true".equals(login)) {
			session.setAttribute("id", id);
			return "list";
		}else {
			model.addAttribute("result",login);
			return "result";
		}
	}
	
}
