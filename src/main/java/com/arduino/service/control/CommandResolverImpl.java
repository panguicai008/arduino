package com.arduino.service.control;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.dao.ControlDao;
import com.arduino.dao.DataDao;
import com.arduino.dao.DeviceDao;
import com.arduino.dao.RoomDao;
import com.arduino.dmo.Control;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;
import com.arduino.dmo.Room;
import com.arduino.util.CharsetDecoder;
import com.arduino.util.CharsetEncoder;
import com.arduino.util.DateToCalendarUtil;
import com.arduino.util.SocketReadThread;
import com.arduino.util.SocketSendThread;



@Service
public class CommandResolverImpl implements CommandResolver{
	
	@Autowired
	private ControlDao controlDao;
	@Override
	public String doResolve(Data data,String[] params) {
		
		
		Data oneData=this.dataDao.findOne(data.getId());
		Device device=oneData.getDevice();
//		Control control=new Control();
//		control.setDevice(device);
//		control.setName(params[0]);
//		control.setMode(params[1]);
//		control.setTemperature(params[2]);
//		control.setWindDirection(params[3]);
//		control.setDate(new Date());
//		this.controlDao.save(control);
		String ip=device.getIp();
		int port=device.getPort();
		String command=ip+"："+port;
		for(int i=0;i<params.length;i++){
			command+="&"+params[i];
		}
		System.out.println(command);
		Control ctrl=this.controlDao.findByNameAndModeAndTemperatureAndWindDirection(params[0],params[1], params[2], params[3]);
		String code=ctrl.getCode();
		Socket socket=SocketReadThread.getSocket(ip);
		System.out.println("socket的IP:"+socket.getLocalAddress().toString());
		SocketSendThread sendThread=new SocketSendThread(socket,code);
		try {
			sendThread.start();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(command);
		return command;
	}
	
	

	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DataDao dataDao;
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public Device resolve(String command) {
		Device device=new Device();
		//<controller>或者<sensor>
		//添加控制器参数信息到device表中
		//<type>controller</type><deviceName></deviceName><ip></ip><port></port><description></description><roomId></roomId>
		String getType=command.substring(command.indexOf("<type>")+6,command.indexOf("</type>"));
		System.out.println(getType);
		if(getType.equalsIgnoreCase("controller")){
			device = new Device();
			String ip=command.substring(command.indexOf("<ip>")+4,command.indexOf("</ip>"));		
			int port =Integer.parseInt(command.substring(command.indexOf("<port>")+6,command.indexOf("</port>")));
			String deviceName=command.substring(command.indexOf("<deviceName>")+12,command.indexOf("</deviceName>"));
			String description =command.substring(command.indexOf("<description>")+13,command.indexOf("</description>"));			
			String roomId=command.substring(command.indexOf("<roomId>")+8,command.indexOf("</roomId>"));
			Room room=this.roomDao.getOne(roomId);
			device.setIp(ip);
			device.setPort(port);
			device.setDeviceName(deviceName);
			device.setDescription(description);			
			device.setRoom(room);
			device.setState(1);
			this.deviceDao.save(device);
		}
		//添加传感器采集数据到data表中
    	//<type>sensor</type><ip></ip><lm35Outlet></lm35Outlet><lm35Intake></lm35Intake><pir></pir><humidity></humidity><light></light>";       
	
		else{
			Data data=new Data();
			
			String ip=command.substring(command.indexOf("<ip>")+4,command.indexOf("</ip>"));
			System.out.println(device.getIp());
			device=this.deviceDao.findDeviceByIp(ip).get(0);			
			data.setDevice(device);
			Double lm35Outlet=Double.parseDouble(command.substring(command.indexOf("<lm35Outlet>")+12,command.indexOf("</lm35Outlet")));
			Double lm35Intake=Double.parseDouble(command.substring(command.indexOf("<lm35Intake>")+12,command.indexOf("</lm35Intake>")));
			Double humidity=Double.parseDouble(command.substring(command.indexOf("<humidity>")+10,command.indexOf("</humidity>")));
			Double light=Double.parseDouble(command.substring(command.indexOf("<light>")+7,command.indexOf("</light>")));
			Double pir=Double.parseDouble(command.substring(command.indexOf("<pir>")+5,command.indexOf("</pir")));
			data.setLm35Outlet(lm35Outlet);
			data.setLm35Intake(lm35Intake);
			data.setHumidity(humidity);
			data.setPir(pir);
			data.setLight(light);
			data.setTime(new Date());
			System.out.println( lm35Outlet+","+lm35Intake+","+humidity+","+light+","+pir+","+pir);
			this.dataDao.save(data);
		}
		return device;
	}
	@Override
	public Device resolver(byte[] command) {
		Device device=new Device();
		//E8E8E8E8E8E8E8E8
		byte[] start=new byte[8];
		//E00001/E00002/E00003
		byte[] commandSymbol=new byte[3];
		//8E8E8E8E8E8E8E8E
		byte[] end=new byte [8];
		//start
		for(int i=0;i<8;i++){
			start[i]=command[i];
		}
		//command
		for(int i=0;i<3;i++){
			commandSymbol[i]=command[i+8];
		}
		//传感器设备工作实时数据
		if(commandSymbol[2]==(byte)0x01){		
			byte[] ip=new byte[4];
			//ip
			for(int i=0;i<4;i++){
				ip[i]=command[i+11];
			}
			//将16进制一字节转换成int
			int ip0=Integer.parseInt(Integer.toHexString(ip[0]));
			int ip1=Integer.parseInt(Integer.toHexString(ip[1]));
			int ip2=Integer.parseInt(Integer.toHexString(ip[2]));
			int ip3=Integer.parseInt(Integer.toHexString(ip[3]));		
			String ipStr=ip0+"."+ip1+"."+ip2+"."+ip3;
			List<Device> deviceList=this.deviceDao.findDeviceByIp(ipStr);
			device=deviceList.get(0);
			if(deviceList.size()!=0){
				byte[] lm35Outlet=new byte[8];
				byte[] lm35Intake=new byte[8];
				byte[] humidity=new byte[8];
				byte[] pir=new byte[8];
				byte[] light=new byte[8];
				//对时间进行处理2012-12-12 12:12:12
				//年月日时分秒各占一字节
				byte[] time=new byte[11];
				//lm35Outlet
				for(int i=0;i<8;i++){
					lm35Outlet[i]=command[i+19]; 
				}
				//lm35Intake
				for(int i=0;i<8;i++){
					lm35Intake[i]=command[i+27];
				}
				//humidity
				for(int i=0;i<8;i++){
					humidity[i]=command[i+35];
				}
				//pir
				for(int i=0;i<8;i++){
					pir[i]=command[i+43];
				}
				//light
				for(int i=0;i<8;i++){
					light[i]=command[i+51];
				}
				//time
				for(int i=0;i<6;i++){
					time[i]=command[i+59];
				}
				//end
				for(int i=0;i<8;i++){
					end[i]=command[i+65];
				}
				Double lm35OutletD=CharsetDecoder.getDouble(lm35Outlet);
				Double lm35IntakeD=CharsetDecoder.getDouble(lm35Intake);
				Double humidityD=CharsetDecoder.getDouble(humidity);
				Double pirD=CharsetDecoder.getDouble(pir);
				Double lightD=CharsetDecoder.getDouble(light);
				String timeStr=time[0]+"-"+time[1]+"-"+time[2]+""+time[3]+":"+time[4]+":"+time[5];
				Date date=DateToCalendarUtil.stringToDate2(timeStr);
				Data data=new Data();
				data.setLm35Outlet(lm35OutletD);
				data.setLm35Intake(lm35IntakeD);
				data.setHumidity(humidityD);
				data.setPir(pirD);
				data.setLight(lightD);
				data.setTime(date);
				data.setDevice(deviceList.get(0));
				this.dataDao.save(data);		
			}			
		}
		//控制器设备工作实时数据
		//<type>controller</type><ip></ip><port></port><deviceName></deviceName><description></description><roomId></roomId>
		if(commandSymbol[2]==(byte)0x02){
			byte[] ip=new byte[4];
			//ip
			for(int i=0;i<4;i++){
				ip[i]=command[i+11];
			}
			//将16进制一字节转换成int
			int ip0=Integer.parseInt(Integer.toHexString(ip[0]));
			int ip1=Integer.parseInt(Integer.toHexString(ip[1]));
			int ip2=Integer.parseInt(Integer.toHexString(ip[2]));
			int ip3=Integer.parseInt(Integer.toHexString(ip[3]));		
			String ipStr=ip0+"."+ip1+"."+ip2+"."+ip3;
			List<Device> deviceList=this.deviceDao.findDeviceByIp(ipStr);
			
			if(deviceList.size()==0){
				byte[] port=new byte[4];
				for(int i=0;i<4;i++){
					port[i]=command[i+15];
				}
				Integer portInt=CharsetDecoder.getInt(port);
				//deivceName最多10个汉字，20个字节
				byte[] deviceName=new byte[20];
				for(int i=0;i<20;i++){
					deviceName[i]=command[i+19];
				}
				String deviceNameStr=CharsetDecoder.getString(deviceName);
				//desciption最多50个汉字，100个字节
				byte[] description=new byte[100];
				for(int i=0;i<100;i++){
					description[i]=command[i+39];
				}
				String descriptionStr=CharsetDecoder.getString(description);
				//型如N5-101
				byte[] roomId=new byte[6];
				for(int i=0;i<6;i++){
					roomId[i]=command[i+139];
				}
				String roomIdStr=CharsetDecoder.getString(roomId);
				device.setDescription(descriptionStr);
				device.setDeviceName(deviceNameStr);
				device.setPort(portInt);
				device.setRoom(this.roomDao.findOne(roomIdStr));
				device.setIp(ipStr);
				device.setState(1);
				this.deviceDao.save(device);
			}	
		}
		return device;
	}

	@Override
	//操作一条data记录，获取其中的device，得到ip和port以及设备参数params
	public byte[] resolve(Data data,String []params) {
		//byte[]为45个字节
		//服务器只对E00003命令符操作
		//起始符
		byte[] start={(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB};
		//命令符
		byte[] commandSymbol={(byte)0xE0,(byte)0x00,(byte)0x03};
		byte[] oneData=new byte[1024];
		for(int i=0;i<start.length;i++){
			oneData[i]=start[i];
		}
		for(int i=0;i<commandSymbol.length;i++){
			oneData[i+8]=commandSymbol[i];
		}
		Device device=this.dataDao.findOne(data.getId()).getDevice();
		//ip中的4个值分成4个字节
		byte[] ipByte=new byte[4];
		for(int i=0;i<ipByte.length;i++){
			ipByte[i]=(byte)Integer.parseInt(device.getIp().split("\\.")[i]);
		}
		for(int i=0;i<4;i++){
			oneData[i+11]=ipByte[i];
		}
		
		//pc的端口号在0-65535之间，最大数值为65535位2的16次方-1
		//只需要4个字节存放Integer，实际int占了8个字节;
		byte[] devicePort4=CharsetEncoder.getBytes(device.getPort());
		for(int i=0;i<4;i++){
			oneData[i+15]=devicePort4[i];
		}
		//name可以是open，或者close，为5个字节
		byte[] name=CharsetEncoder.getBytes(params[0]);
		
		if(name.length==4){
			byte[]nameByte={(byte)0x00,name[0],name[1],name[2],name[3]};
			for(int i=0;i<5;i++){
				oneData[i+19]=nameByte[i];
			}
		}
		else{
			for(int i=0;i<5;i++){
				oneData[i+19]=name[i];
			}
		}
		//mode:制热 制冷 除湿 为4个字节
		byte[] mode=CharsetEncoder.getBytes(params[1]);	
		for(int i=0;i<4;i++){
			oneData[i+24]=mode[i];
		}
		//温度为17-20 26-29 度为4个字节，不考虑将数字和汉字分开为3个字节
		byte[] temperature=CharsetEncoder.getBytes(params[2]);
		for(int i=0;i<4;i++){
			oneData[i+28]=temperature[i];
		}
		//风向为up down right left为5个字节
		byte[] windDirection=CharsetEncoder.getBytes(params[3]);
		if(windDirection.length==2){
			byte[]up={(byte)0x00,(byte)0x00,(byte)0x00,windDirection[0],windDirection[1]};
			for(int i=0;i<5;i++){
				oneData[i+32]=up[i];
			}
		}
		if(windDirection.length==4){
			byte[]downOrLeft={(byte)0x00,windDirection[0],windDirection[1],windDirection[2],windDirection[3]};
			for(int i=0;i<5;i++){
				oneData[i+32]=downOrLeft[i];
			}
		}
		if(windDirection.length==5){
			for(int i=0;i<5;i++){
				oneData[i+32]=windDirection[i];
			}
		}
		//结束符
		byte[] end={(byte)0xBA,(byte)0xBA,(byte)0xBA,(byte)0xBA,(byte)0xBA,(byte)0xBA,(byte)0xBA,(byte)0xBA};
		for(int i=0;i<8;i++){
			oneData[i+37]=end[i];
		}
		
		return oneData;
	}
	
	
	

}
