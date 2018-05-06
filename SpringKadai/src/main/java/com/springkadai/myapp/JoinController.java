package com.springkadai.myapp;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
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
public class JoinController {
	
	@Autowired
	private LoginDao loginDao;
	private UserDao userDao;
	private UserDetailDao userdetailDao;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, String id, String pass, String name, String kana, Date birth, String club, String cmd, HttpSession session, HttpServletRequest req) {
		if("joinCheck".equals(cmd)) {
			
			UserVo user = new UserVo(id, pass, name, kana);
			UserDetailVo userdetail = new UserDetailVo(id, birth, club);
			
			model.addAttribute("user", user);
			model.addAttribute("userdetail", userdetail);
			
			return "joinCheck";
		}

		System.out.println(id);
		System.out.println(pass);
		System.out.println(name);
		System.out.println(kana);
		
		int n = userDao.insert(id, pass, name, kana);
		
		System.out.println("n : " + n);
		if(n>0) {
			userdetailDao.insert(id, birth, club);
			session.setAttribute("id", id);
			req.setAttribute("result", "データを登録しました。");
			
			return "result";
		}else {
			req.setAttribute("result", "既に使用されているため、使用できません。");
			
			if(session.getAttribute("id") != null) {
				return "result";
			}else {
				req.setAttribute("cmd", "fail");
				return "result";
			}
		}
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(Model model, String id) {
		return loginDao.idCheck(id);
	}

}
