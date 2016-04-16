package com.arduino.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.arduino.dmo.Device;
import com.arduino.service.control.CommandResolver;
import com.arduino.service.device.DeviceService;


public class SocketReadThread extends Thread {

	public SocketReadThread(Socket socket, CommandResolver commandResolver) {
		super();
		this.socket = socket;
		this.commandResolver = commandResolver;
		
	}
	@Autowired
	private DeviceService deviceService;
	private String command;
	private Socket socket;
	private Device device;
	@Autowired
	private CommandResolver commandResolver;

	public String getCommand() {
		return command;
	}
	public static Socket getSocket(String ip){
		return socketMap.get(ip);
	}
	static HashMap<String ,Socket>socketMap=new HashMap<String,Socket>();

	@Override
	public void run() {
		if (this.socket == null || !socket.isConnected()) {
			return;
		}
		BufferedReader reader = null;
		StringBuilder builder=new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("客户端："+socket.getInetAddress()+":"+socket.getPort()+"has connected");
			String clientIp=socket.getInetAddress().getHostAddress();
			System.out.println(clientIp);
			socketMap.put(clientIp, socket);
			
			String tmp=null;
			while((tmp=reader.readLine())!=null){
				builder.append(tmp);
				this.command=builder.toString();
				this.device=commandResolver.resolve(command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		finally {
//			if (null != reader) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
