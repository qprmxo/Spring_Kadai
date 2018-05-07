package com.springkadai.myapp;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springkadai.myapp.dao.LoginDao;
import com.springkadai.myapp.dao.UserDao;
import com.springkadai.myapp.dao.UserDetailDao;
import com.springkadai.myapp.vo.UserVo;
import com.springkadai.myapp.vo.UserDetailVo;

@Controller
public class UserController {
	
	@Autowired private LoginDao loginDao;
	@Autowired private UserDao userDao;
	@Autowired private UserDetailDao userdetailDao;
	
	
	// Login
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
			return "search";
		}else {
			model.addAttribute("result",login);
			model.addAttribute("cmd","fail");
			return "result";
		}
	}
	
	
	// Logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("result", "ログアウトしました。");
		model.addAttribute("cmd", "fail");
		return "result";
	}
	
	
	// insert
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, String id, String pass, String name, String kana, Date birth, String club, String cmd, HttpSession session) {
		if("joinCheck".equals(cmd)) {
			
			UserVo user = new UserVo(id, pass, name, kana);
			UserDetailVo userdetail = new UserDetailVo(id, birth, club);
			
			model.addAttribute("user", user);
			model.addAttribute("userdetail", userdetail);
			
			return "joinCheck";
		}

		int n = userDao.insert(id, pass, name, kana);
		
		if(n>0) {
			userdetailDao.insert(id, birth, club);
			session.setAttribute("id", id);
			model.addAttribute("result", "データを登録しました。");
			
			return "result";
		}else {
			model.addAttribute("result", "既に使用されているため、使用できません。");
			
			if(session.getAttribute("id") != null) {
				return "result";
			}else {
				model.addAttribute("cmd", "fail");
				return "result";
			}
		}
	}
	
	
	// idCheck (ajax)
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(Model model, String id) {
		return loginDao.idCheck(id);
	}
	
	
	// search (select)
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {
		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Model model, String id, String name, String kana) {
		ArrayList<UserVo> list = userDao.select(id, name, kana);
		
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("kana", kana);
		
		return "search";
	}
	
	
	// delete 
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCheck(Model model, String id) {
		UserVo user = userDao.find(id);
		UserDetailVo userdetail = userdetailDao.find(id);
		
		model.addAttribute("user", user);
		model.addAttribute("userdetail", userdetail);
		
		return "delete";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, String id) {
		
		userdetailDao.delete(id);
		userDao.delete(id);
		
		model.addAttribute("result", "データを削除しました。");
		return "result";		
	}

	
	// update
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, String id) {
		
		UserVo user = userDao.find(id);
		UserDetailVo userdetail = userdetailDao.find(id);
		
		model.addAttribute("user", user);
		model.addAttribute("userdetail", userdetail);
		
		return "update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, String id, String name, String kana, Date birth, String club, String cmd, HttpSession session) {
		
		if("updateCheck".equals(cmd)) {
			
			UserVo user = new UserVo(id, name, kana, birth, club);
			model.addAttribute("user", user);
			
			return "updateCheck";			
		}
		
		userDao.update(id, name, kana);
		userdetailDao.update(id, birth, club);		
		
		model.addAttribute("result", "データを更新しました。");
		
		return "result";
	}
}
