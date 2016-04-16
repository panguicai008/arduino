package com.arduino.util;

import java.nio.charset.Charset;

public class CharsetEncoder{
	private static final Charset CHARSET=Charset.forName("gbk");
	//将基本数据类型转换成byte
	public static byte[] getBytes(short data){
	    byte[] bytes = new byte[2];
	    bytes[0] = (byte) (data & 0xff);
	    bytes[1] = (byte) ((data & 0xff00) >> 8);
	    return bytes;
    }
	public static byte[] getBytes(char data){
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }
	public static byte[] getBytes(Integer data){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }
	
	public static byte[] getBytes(long data){
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }
	public static byte[] getBytes(float data){
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
    }
	public static byte[] getBytes(Double data){
		long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);	
	}
	public static byte[] getBytes(String data, Charset charsetName){
		
		return data.getBytes(CHARSET);       
    }
    public static byte[] getBytes(String data){
        return getBytes(data,CHARSET);
    }
    public static String toHex(String str){
		String result="";	
		String res="";
		for(int i=0;i<str.length();i++){
			byte[] by=str.substring(i,i+1).getBytes();
			String temp=Integer.toHexString(by[0]& 0xFF);
			result="(byte)"+"0x"+temp.toUpperCase()+",";
			res+=result;
			if(by.length==2){
				temp=Integer.toHexString(by[1]& 0xff);
				result="(byte)"+"0x"+temp.toUpperCase()+",";
				res+=result;		   
			}		
		}
		res=res.substring(0,res.length()-1);
		System.out.println(res);
		return res;
	}
    
	
    
	
	
}
