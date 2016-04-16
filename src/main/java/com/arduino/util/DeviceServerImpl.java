package com.arduino.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.service.control.CommandResolver;


@Service
public class DeviceServerImpl extends Thread implements DeviceServer {
	
	private ServerSocket server;
	@Autowired
	private CommandResolver commandResolver;
	private String command;
	@Override
	public void run() {
		this.executor = Executors.newCachedThreadPool();
		while (this.isRunning) {
			try {
				Socket socket = server.accept();
				SocketReadThread thread = new SocketReadThread(socket, commandResolver);
				
//				SocketSendThread sendThread=new SocketSendThread(socket,command);
//				try {
//					sendThread.call();
//					
//				} catch (Exception e) {
//					
//					e.printStackTrace();
//				}

				thread.setDaemon(true);
				executor.execute(thread);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ExecutorService executor;

	public ExecutorService getExecutor() {
		return executor;
	}

	@Override
	@PostConstruct
	public void startServer() {
		try {
			this.server = new ServerSocket(9090);
			this.setDaemon(true);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isRunning = true;

	@Override
	@PreDestroy
	public void stopServer() {
		this.isRunning = false;
		try {
			if (!this.server.isClosed()) {
				this.server.close();
			}
			this.executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
