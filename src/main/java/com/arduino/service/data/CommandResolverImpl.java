//package com.whck.service.dc;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.whck.dao.DcDao;
//import com.whck.dao.DeviceDao;
//import com.whck.dao.DeviceTimeDao;
//import com.whck.dao.VariableDao;
//import com.whck.dao.ZoneDao;
//import com.whck.dmo.Dc;
//import com.whck.dmo.Device;
//import com.whck.dmo.DeviceTime;
//import com.whck.dmo.Variable;
//import com.whck.dmo.Zone;
//import com.whck.util.CharsetDecoder;
//import com.whck.util.CharsetEncoder;
//import com.whck.util.DateToCalendarUtil;
//
//@Service
//public class CommandResolverImpl implements CommandResolver{
//	@Autowired
//	private ZoneDao zoneDao;
//	private DcDao dcDao;
//	private VariableDao variableDao;
//	private DeviceDao deviceDao;
//	private DeviceTimeDao deviceTimeDao;
//	@Override
//	public Dc resolve(byte[] command) {
//		
//		byte[] start=new byte[8];
//		byte[] commandSymbol=new byte[3];
//		byte[] id=new byte[11];
//		byte[] longitude=new byte[3];
//		byte[] latitude=new byte[3];
//		byte[] length=new byte[2];
//		byte[] data=new byte[1024];
//		byte[] crc=new byte[2];
//		byte[] end=new byte [8];
//		
//		for(int i=0;i<8;i++){
//			start[i]=command[i];
//		}
//		//command
//		for(int i=0;i<3;i++){
//			commandSymbol[i]=command[i+8];
//		}
//		//id
//		for(int i=0;i<11;i++){
//			id[i]=command[i+11];
//		}
//		//longitude
//		for(int i=0;i<3;i++){
//			longitude[i]=command[i+22]; 
//		}
//		//latitude
//		for(int i=0;i<3;i++){
//			latitude[i]=command[i+25];
//		}
//		//先把length的值取出来然后获取数据
//		for(int i=0;i<2;i++){
//			length[i]=command[i+28];
//		}
//		
//		short len=CharsetDecoder.getShort(length);
//		int dataLen=len-10;//字节
//		//数据存储到data数组中
//		for(int i=0;i<len-10;i++){
//			data[i]=command[i+30];
//		}
//		//crc
//		for(int i=0;i<2;i++){
//			crc[i]=command[i+30+len-10];
//		}
//		//end
//		for(int i=0;i<8;i++){
//			end[i]=command[i+30+len-10+2];
//		}
//		//将各byte数组进行转换
//		//对命令符进行分析（三种）
//		String dcId=CharsetDecoder.getString(id);
//		String longitudeStr=CharsetDecoder.getString(longitude);
//		String latitudeStr=CharsetDecoder.getString(latitude);
//		//先根据纬度判断地区是否存在，根据dcId判断数据中心是否存在
//		//都存在的情况下，再判断dc对应的地区是否为该地区
//		Zone zone=this.zoneDao.findByLongitudeAndLatitude(longitudeStr, latitudeStr);
//		Dc dc=this.dcDao.findOne(dcId);
//		
//		try{
//			if(zone!=null&& dc!=null&&dc.getZone()==zone){
//				if(commandSymbol[2]==(byte)0x01){
//					//传感器设备工作实时数据
//					byte[] name=new  byte[20];
//					byte[] value=new byte[8];
//					byte[] unit=new byte[12];
//					for(int i=0;i<20;i++){
//						name[i]=data[i];
//					}
//					for(int i=0;i<8;i++){
//						value[i]=data[i+20];
//					}
//					for(int i=0;i<12;i++){
//						unit[i]=data[i+28];
//					}
//					String nameStr=CharsetDecoder.getString(name);
//					String unitStr=CharsetDecoder.getString(unit);
//					double valueDouble=CharsetDecoder.getDouble(value);
//					Variable var=new Variable();
//					var.setName(nameStr);
//					var.setUnit(unitStr);
//					var.setValue(valueDouble);
//					this.variableDao.save(var);
//					
//					
//				}
//				if(commandSymbol[2]==(byte)0x02){
//					//控制器设备工作实时数据
//					byte[] name=new byte[20];
//					for(int i=0;i<20;i++){
//						name[i]=data[i];
//					}
//					byte[] state={data[20]};
//					byte[] type={data[21]};//对应数据库表中的ctrl_way字段
//					byte[] ctrlMode={data[22]};//对应数据库表中的ctrl_mode字段
//					byte[] extentValidBit={data[23]};//控制器开度有效位
//					byte[] extentSize={data[24]};//控制器开度大小
//					String nameStr=CharsetDecoder.getString(name);
//					//将16进制一字节转换成int
//					int typeInt=Integer.parseInt(Integer.toHexString(type[0]));
//					int ctrlModeInt=Integer.parseInt(Integer.toHexString(ctrlMode[0]));
//					int ValidBitInt=Integer.parseInt(Integer.toHexString(extentValidBit[0]));
//					int extentSizeInt=Integer.parseInt(Integer.toHexString(extentSize[0]));
//					Device device=new Device();
//					device.setDeviceName(nameStr);
//					device.setCtrlMode(ctrlModeInt);
//					device.setCtrlWay(typeInt);
//					device.setDc(dc);
//					this.deviceDao.save(device);
//					
//				}
//				if(commandSymbol[2]==(byte)0x03){
//					//控制器设备参数读取与配置
//					//先读取控制设备
//					byte[] deviceName=new byte[20];
//					for(int i=0;i<20;i++){
//						deviceName[i]=data[i+30];
//					}
//					String deviceNameStr=CharsetDecoder.getString(deviceName);
//					
//					List<Device> deviceList=this.deviceDao.findByDeviceName(deviceNameStr);
//					for(Device device:deviceList){
//						if(device.getDc()==dc){
//							//将控制设备的时间设置存放到DeviceTime表中
//							for(int i=0;i<4;i++){
//								DeviceTime deviceTime=new DeviceTime();
//								deviceTime.setDevice(device);
//								byte hh=(byte)data[10*i+20];
//								byte mm=(byte)data[10*i+21];
//								byte ss=(byte)data[10*i+22];
//								byte hh2=(byte)data[10*i+23];
//								byte mm2=(byte)data[10*i+24];
//								byte ss2=(byte)data[10*i+25];
//								byte[]run={data[10*i+26],data[10*i+27]};
//								byte[] stop={data[10*i+28],data[10*i+29]};
//								Integer h=Integer.parseInt(Integer.toHexString(hh));
//								Integer m=Integer.parseInt(Integer.toHexString(mm));
//								Integer s=Integer.parseInt(Integer.toHexString(ss));
//								Integer h2=Integer.parseInt(Integer.toHexString(hh2));
//								Integer m2=Integer.parseInt(Integer.toHexString(mm2));
//								Integer s2=Integer.parseInt(Integer.toHexString(ss2));
//								Calendar cal=new GregorianCalendar();
//								int year=cal.get(Calendar.YEAR);
//								int month=cal.get(Calendar.MONTH)+1;//Calendar.MONTH为0；
//								int day=cal.get(Calendar.DAY_OF_MONTH);
//								String hhh,mmm,sss;
//								if(h<10) 
//									hhh = "0"+h; 
//								else 
//									hhh= h+"";	
//								if(m<10) 
//									mmm = "0"+m; 
//								else 
//									mmm = m+""; 
//								if(s<10) 
//									sss = "0"+s; 
//								else 
//									sss = s+"";
//								String time1Str=year+"-"+month+"-"+day+""+hhh+":"+mmm+":"+sss;
//								Date time1=DateToCalendarUtil.stringToDate(time1Str);
//								if(h2<10) 
//									hhh = "0"+h2; 
//								else 
//									hhh= h2+"";	
//								if(m2<10) 
//									mmm = "0"+m2; 
//								else 
//									mmm = m2+""; 
//								if(s2<10) 
//									sss = "0"+s2; 
//								else 
//									sss = s+"";
//								String time2Str=year+"-"+month+"-"+day+""+hhh+":"+mmm+":"+sss;
//								Date time2=DateToCalendarUtil.stringToDate(time2Str);
//								short runNumber=CharsetDecoder.getShort(run);
//								int runNumbers=runNumber+0;
//								short stopNumber=CharsetDecoder.getShort(stop);
//								int stopNumbers=stopNumber+0;
//								deviceTime.setStartTime(time1);
//								deviceTime.setEndTime(time2);
//								deviceTime.setRunTime(runNumbers);
//								deviceTime.setStopTime(stopNumbers);											
//							}
//							//对传感器进行操作
//							for(int i=0;i<4;i++){
//								byte sensorValidBit=data[37*i+60];
//								byte[] sensorName=new byte[20];
//								byte[] maxValue=new byte[8];
//								byte[] minValue=new byte[8];
//								for(int j=0;j<20;j++){
//									sensorName[j]=data[37*i+60+1+j];
//								}
//								String sensorNameStr=CharsetDecoder.getString(sensorName);
//								for(int j=0;j<8;j++){
//									maxValue[j]=data[37*i+60+1+20+j];
//								}
//								Double max=CharsetDecoder.getDouble(maxValue);
//								for(int j=0;i<8;j++){
//									minValue[j]=data[37*i+60+1+20+8+j];
//								}
//								Double min=CharsetDecoder.getDouble(minValue);
//								Variable var=new Variable();
//								var.setMaxValue(max);
//								var.setMinValue(min);
//								var.setName(sensorNameStr);
//								if(sensorValidBit==(byte)0x00){
//									var.setDevice(device);
//								}
//								else{
//									var.setDevice(null);
//								}			
//							}
//						}
//					}			
//				}
//			}
//		}catch(Exception e){
//			//此处要将服务器获取信息是否成功的消息反馈给客户端
//			e.printStackTrace();
//		}	
//		return dc;
//	}
//
//	@Override
//	//解决的应该是一个设备才好
//	public byte[] deResolve(Dc dc) {
//		//服务器只对E00003操作
//		byte[] start={(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB,(byte)0xAB};
//		byte[] commandSymbol={(byte)0xE0,(byte)0x00,(byte)0x03};
//		byte[] dcId=CharsetEncoder.getBytes(dc.getId());
//		Zone zone=dc.getZone();
//		byte[] longitude=CharsetEncoder.getBytes(zone.getLongitude());
//		byte[] latitude=CharsetEncoder.getBytes(zone.getLatitude());
//		byte [] length={};
//		//获取控制设备名称，然后得到DeviceTime表
//		//对四个时间段和四个传感器进行处理
//		String deviceNameStr="";
//		String sensorNameStr="";
//		int len=sensorNameStr.length();
//		List<Device> deviceList=this.deviceDao.findByDeviceName(deviceNameStr);
//		Device device=new Device();
//		for(Device d:deviceList){
//			if(d.getDc()==dc){
//				device=d;
//			}
//		}
//		
//		
//		List<DeviceTime>deviceTimeList=this.deviceTimeDao.findByDevice(device);
//		//deviceTimeList的长度为4
//		byte[]time=new byte[40];
//		for(int i=0;i<4;i++){
//			DeviceTime deviceTime=deviceTimeList.get(i);
//			deviceTime.getStartTime();
//			String startTimeStr=DateToCalendarUtil.dateToString(deviceTime.getStartTime());
//			//2016-02-25 09:26:30
//			String hh=startTimeStr.substring(11,13);
//			String mm=startTimeStr.substring(14,16);
//			String ss=startTimeStr.substring(17,19);
//			byte hhByte=(byte)Integer.parseInt(hh);
//			byte mmByte=(byte)Integer.parseInt(mm);
//			byte ssByte=(byte)Integer.parseInt(ss);
//			deviceTime.getEndTime();
//			String endTimeStr=DateToCalendarUtil.dateToString(deviceTime.getEndTime());
//			String hh2=endTimeStr.substring(11,13);
//			String mm2=endTimeStr.substring(14,16);
//			String ss2=endTimeStr.substring(17,19);
//			byte hh2Byte=(byte)Integer.parseInt(hh2);
//			byte mm2Byte=(byte)Integer.parseInt(mm2);
//			byte ss2Byte=(byte)Integer.parseInt(ss2);
//			byte[] runByte4=CharsetEncoder.getBytes(deviceTime.getRunTime());			
//			byte[] stopByte4=CharsetEncoder.getBytes(deviceTime.getStopTime());
//			byte[] runByte2={runByte4[2],runByte4[3]};
//			byte[] stopByte2={stopByte4[2],stopByte4[3]};
//			byte[] time2={hhByte,mmByte,ssByte,hh2Byte,mm2Byte,ss2Byte,runByte2[0],runByte2[1],stopByte2[0],stopByte2[1]};
//			
//		}
//		
//		List<Variable>variableList =this.variableDao.findByDevice(device);
//		//variableList的长度为4
//		byte [] variable =new byte[144];
//		for(int i=0;i<4;i++){
//			Variable v=variableList.get(i);
//			int nameLen=v.getName().length();
//			byte[] sensorName=new byte[20]; 
//			for(int j=0;i<nameLen;j++){
//				//如果长度是6，就得补齐14个(byte)0x00
//				byte[] name=CharsetEncoder.getBytes(v.getName());
//				
//				for(int n=0;n<20-nameLen;n++){
//					sensorName[n]=(byte)0x00;
//					
//				}
//				for(int m=0;m<nameLen;m++){
//					sensorName[20-nameLen+m]=name[m];
//				}
//			}
//			byte [] max=CharsetEncoder.getBytes(v.getMaxValue());
//			byte[] min=CharsetEncoder.getBytes(v.getMinValue());
//				
//		}
//		
//		
//		
//		return null;
//	}
//
//}
