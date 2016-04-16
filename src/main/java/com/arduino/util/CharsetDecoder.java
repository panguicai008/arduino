package com.arduino.util;

import java.nio.charset.Charset;

public class CharsetDecoder {
	private static final Charset CHARSET=Charset.forName("gbk");
	public static short getShort(byte[] bytes){
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }
    public static char getChar(byte[] bytes){
        return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    public static int getInt(byte[] bytes){
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }
    public static long getLong(byte[] bytes){
        return(0xffL & (long)bytes[0]) | (0xff00L & ((long)bytes[1] << 8)) | (0xff0000L & ((long)bytes[2] << 16)) | (0xff000000L & ((long)bytes[3] << 24))
         | (0xff00000000L & ((long)bytes[4] << 32)) | (0xff0000000000L & ((long)bytes[5] << 40)) | (0xff000000000000L & ((long)bytes[6] << 48)) | (0xff00000000000000L & ((long)bytes[7] << 56));
    }
    public static float getFloat(byte[] bytes)
    {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes){
        long l = getLong(bytes);
        return Double.longBitsToDouble(l);
    }

    public static String getString(byte[] bytes, Charset charsetName){
        return new String(bytes,CHARSET);
    }
    public static String getString(byte[] bytes){
        return getString(bytes,CHARSET);
    }
}
