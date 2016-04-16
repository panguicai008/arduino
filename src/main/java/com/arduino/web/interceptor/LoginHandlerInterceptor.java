package com.arduino.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.arduino.dmo.User;
import com.arduino.web.keys.Keys;

public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean rt = false;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Keys.LOGIN_SESSION_DATA);
		if (user != null && user.getLevel()==1) {
			rt = true;
		} else {
			request.setAttribute(Keys.LOGIN_ERROR_TYPE, "500");
			request.setAttribute(Keys.LOGIN_ERROR_MSG, "您没有管理员权限");
			request.getRequestDispatcher("/login/error.do").forward(request, response);
		}
		return rt;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
