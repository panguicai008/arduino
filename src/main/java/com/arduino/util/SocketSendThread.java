package com.arduino.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;

import com.arduino.dao.DataDao;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;
import com.arduino.service.control.CommandResolver;
import com.arduino.service.device.DeviceService;
import com.arduino.web.controller.CtrlController;



public class SocketSendThread extends Thread{
	
	private String command;
	private Socket socket;
	public SocketSendThread(Socket socket,String command) {
		this.socket=socket;
		this.command=command;
	}

	
	

	@Override
	public void run() {
		
		BufferedWriter writer = null;
		try {
			
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			writer.write(command);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
//			if (socket != null) {
//				try {
//					socket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
	}
}
