package com.arduino.service.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.dao.DataDao;
//import com.arduino.dao.DcDao;
import com.arduino.dao.DeviceDao;
import com.arduino.dao.RoomDao;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;
import com.arduino.dmo.Room;
import com.arduino.util.SocketUtil;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	private static final Log log = LogFactory.getLog(DeviceServiceImpl.class);

	private ServerSocket server;
	@Autowired
	private SocketUtil socketUtil;
	@Autowired
	private DeviceDao deviceDao;

	private ExecutorService executor;

//	@PostConstruct
//	public void openServerSocket() {
//		this.executor = Executors.newFixedThreadPool(1);
//		executor.execute(this);
//	}
//
//	@PreDestroy
//	public synchronized void closeServerSocket() {
//		try {
//			executor.shutdown();
//			if (!this.server.isClosed()) {
//				this.server.close();
//			}
//			log.debug("serverSocket是否已经关闭：" + this.server.isClosed());
//		} catch (IOException e) {
//		}
//	}

	@Override
	public List<Device> findAll() {
		return this.deviceDao.findAll();
	}

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private DataDao dataDao;

//	@Override
//	public void run() {
//		try {
//			this.server = new ServerSocket(SocketUtil.SERVER_SOCKET_PORT);
//			log.debug("运行ServerSocket服务器,端口为：" + SocketUtil.SERVER_SOCKET_PORT);
//			while (!this.server.isClosed()) {
//				Socket socket = server.accept();
//				//添加无线模块的参数信息到device表中
//				String tmp = this.socketUtil.readString(socket);
//				Device device=new Device();
//				device.setIp(socket.getInetAddress().getHostAddress());
//				device.setPort(socket.getPort());			
//				device.setState(1);
//				String deviceName=tmp.substring(tmp.indexOf("<deviceName>")+12,tmp.indexOf("</deviceName>"));
//				String description =tmp.substring(tmp.indexOf("<description>")+13,tmp.indexOf("</description>"));			
//				String roomId=tmp.substring(tmp.indexOf("<roomId>")+8,tmp.indexOf("</roomId>"));
//				Room room=this.roomDao.getOne(roomId);
//				device.setDeviceName(deviceName);
//				device.setDescription(description);			
//				device.setRoom(room);
//				this.deviceDao.save(device);
//				//添加传感器采集数据到data表中
//				Data data=new Data();
//				
//				String ip=tmp.substring(tmp.indexOf("<ip>")+4,tmp.indexOf("</ip>"));	
//				Device de=this.deviceDao.findDeviceByIp(ip).get(0);
//				System.out.println(de.getIp());
//				data.setDevice(de);
//				Double lm35Outlet=Double.parseDouble(tmp.substring(tmp.indexOf("<lm35Outlet>")+12,tmp.indexOf("</lm35Outlet")));
//				Double lm35Intake=Double.parseDouble(tmp.substring(tmp.indexOf("<lm35Intake>")+12,tmp.indexOf("</lm35Intake>")));
//				Double humidity=Double.parseDouble(tmp.substring(tmp.indexOf("<humidity>")+10,tmp.indexOf("</humidity>")));
//				Double light=Double.parseDouble(tmp.substring(tmp.indexOf("<light>")+7,tmp.indexOf("</light>")));
//				Double pir=Double.parseDouble(tmp.substring(tmp.indexOf("<pir>")+5,tmp.indexOf("</pir")));
//				data.setLm35Outlet(lm35Outlet);
//				data.setLm35Intake(lm35Intake);
//				data.setHumidity(humidity);
//				data.setPir(pir);
//				data.setLight(light);
//				data.setTime(new Date());
//				System.out.println( lm35Outlet+","+lm35Intake+","+humidity+","+light+","+pir+","+pir);
//				this.dataDao.save(data);
//				
//				
////				ObjectMapper mapper = new ObjectMapper();
////				
////				Device device = mapper.readValue(tmp, Device.class);
//				
//			
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public List<Device> findDeviceByRoom(String roomId) {
		
		return this.deviceDao.findDeviceByRoom(roomId);
	}

	@Override
	public List<Device> findDeviceByIp(String ip) {
		
		return this.deviceDao.findDeviceByIp(ip);
	}

	@Override
	public Map<Integer, Boolean> sendCommand(Device device) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Device device) {
		this.deviceDao.save(device);
		
	}

}
