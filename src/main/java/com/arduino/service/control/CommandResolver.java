package com.arduino.service.control;
import java.net.Socket;

import com.arduino.dmo.Control;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;

public interface CommandResolver {
	/*
	 * @param name open或者close
	 * @param data 数据
	 * @param params格式 open mode temperature windDirection close
	 */
	
	public String doResolve(Data data,String[]params);
	public byte[] resolve(Data data,String [] params);
	public Device resolve(String command);
	public Device resolver(byte[] command);
}
