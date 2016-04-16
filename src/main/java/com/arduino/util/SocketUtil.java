package com.arduino.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.stereotype.Component;

@Component
public class SocketUtil {
	/*
	 * public Device getDevice(String str) { Device device = new Device(); int
	 * index = str.indexOf("<IP>"); if (index >= 0) { String ip =
	 * str.substring(index + 4, str.indexOf("</IP>")); device.setIp(ip); } index
	 * = str.indexOf("<DEVICENAME>"); if (index >= 0) { String deviceName =
	 * str.substring(index + 12, str.indexOf("</DEVICENAME>"));
	 * device.setDeviceName(deviceName); } index = str.indexOf("<DC>"); if
	 * (index >= 0) { String id = str.substring(index + 4,
	 * str.indexOf("</DC>")); Dc dc = new Dc(); dc.setId(id); device.setDc(dc);
	 * } index = str.indexOf("<DESCRIPTION>"); if (index >= 0) { String
	 * description = str.substring(index + 13, str.indexOf("</DESCRIPTION>"));
	 * device.setDescription(description); } index = str.indexOf("<CTRL_WAY>");
	 * if (index >= 0) { String ctrlWay = str.substring(index + 10,
	 * str.indexOf("</CTRL_WAY>"));
	 * device.setCtrlWay(Integer.valueOf(ctrlWay.trim())); } index =
	 * str.indexOf("<CTRL_MODE>"); if (index >= 0) { String ctrlMode =
	 * str.substring(index + 11, str.indexOf("</CTRL_MODE>"));
	 * device.setCtrlMode(Integer.valueOf(ctrlMode.trim())); } index =
	 * str.indexOf("<STATE>"); if (index >= 0) { String state =
	 * str.substring(index + 7, str.indexOf("</STATE>"));
	 * device.setState(Integer.valueOf(state)); } return device; }
	 */

	public static final int SERVER_SOCKET_PORT = 9090;

	/**
	 * 
	 * @param socket
	 *            获取输入流中的字符串
	 * @return
	 */
	public String readString(Socket socket) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = this.getReader(socket);
		String msg = null;
		try {
			while ((msg = reader.readLine()) != null) {
				builder.append(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * 
	 * @param socket
	 *            获取套接字的输入流
	 * @return
	 */
	public BufferedReader getReader(Socket socket) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	/**
	 * 
	 * @param socket获取套接字的输出流
	 * @return
	 */
	public PrintWriter getWriter(Socket socket) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return printWriter;
	}
}
