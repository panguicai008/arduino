package com.arduino.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.dao.UserDao;
import com.arduino.dmo.User;
import com.arduino.service.email.EmailService;
import com.arduino.service.user.UserService;
import com.arduino.util.MD5Util;
import com.arduino.web.keys.Keys;

@RequestMapping("register")
@Controller
public class RegisterController {

	@RequestMapping("page.do")
	public String page() {
		return "register";
	}

	@Autowired
	private UserDao userDao;

	@RequestMapping("sendCode.do")
	@ResponseBody
	public Map<String, Object> sendCode(String email, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User u = this.userDao.findOne(email);
		if (u != null) {
			map.put("msg", "该邮箱已被使用");
			return map;
		}
		int x = 100000 + new Random().nextInt(899999);
		session.setAttribute(Keys.REGISTER_ACTIVE_CODE, x);
		try {
			this.emailService.sendCode(email, "激活码为：" + x);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			return map;
		}
		return map;
	}

	@RequestMapping(method = RequestMethod.POST, value = "add.do")
	public String add(User user , HttpSession session) {
		String rt = null;
		if (session.getAttribute(Keys.REGISTER_ACTIVE_CODE) == null) {
			return "register";
		}
		if (user.getValidateCode().equals(session.getAttribute(Keys.REGISTER_ACTIVE_CODE).toString())) {
			user.setRegDate(new Date());
			user.setPassword(MD5Util.getSecurityCode(user.getPassword()));
			this.userService.add(user);
			rt = "login";
		} else {
			rt = "register";
		}
		return rt;
	}

	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
}
