package com.arduino.service.control;

import com.arduino.util.CharsetEncoder;

public class Test {

	public static void main(String[] args) {
//		String ip="192.169.0.1";
//		byte[] test=CharsetEncoder.getBytes(ip);
//		System.out.println(test.length);
//		byte[] ipByte=new byte[4];
//		for(int i=0;i<ipByte.length;i++){
//			String[] ipStr="192.168.0.1".split("\\.");
//			System.out.println(ipStr.length);
//			System.out.println(ipStr[0]+","+ipStr[1]+","+ipStr[2]+","+ipStr[3]);
//			ipByte[i]=(byte)Integer.parseInt("192.168.0.1".split("\\.")[i]);
//		}
//		System.out.println(ipByte[0]);
		Integer port=65535;
		String name="open";
		String close="close";//5
		String mode="制冷";//4
		String temperature="36度";//3(1+2)
		String windDirection="up";//down/left/right 5
		System.out.println(CharsetEncoder.getBytes(port).length);
		System.out.println(CharsetEncoder.getBytes(name).length);
		System.out.println(CharsetEncoder.getBytes(close).length);
		System.out.println(CharsetEncoder.getBytes(mode).length);
		System.out.println(CharsetEncoder.getBytes(temperature).length);
		System.out.println(CharsetEncoder.getBytes(windDirection).length);

	}

}
