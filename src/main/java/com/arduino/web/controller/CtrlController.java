package com.arduino.web.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.dao.ControlDao;
import com.arduino.dao.DataDao;
import com.arduino.dao.DeviceDao;
import com.arduino.dmo.Control;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;
import com.arduino.service.control.CommandResolver;

@Controller
@RequestMapping("ctrl")
public class CtrlController {
	@Autowired
	private ControlDao controlDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DataDao dataDao;
	
	
	
	@Autowired
	private CommandResolver commandResolver;
	@RequestMapping("open.do")
	@ResponseBody
	public Map<String,Object>open(Data data,String name,String mode,String temperature,String windDirection){
		Map<String ,Object> map=new HashMap<>();
		String [] params={name,mode,temperature,windDirection};
		this.commandResolver.doResolve(data,params);
		map.put("success",true);
		return map;
	}
	@RequestMapping("close.do")
	
	@ResponseBody
	public Map<String,Object>close(Data data,String name,String mode,String temperature,String windDirection){
		Map<String ,Object> map=new HashMap<>();
		String [] params={name,mode,temperature,windDirection};
		this.commandResolver.doResolve(data,params);
		map.put("success",true);
		return map;
	}
	
}
