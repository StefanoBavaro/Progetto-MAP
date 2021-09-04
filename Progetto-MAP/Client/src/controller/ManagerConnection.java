package controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

class ManagerConnection {
	private Socket socket;
	private ObjectOutputStream ou;
	private ObjectInputStream in;
	
	// SINGLETON CLASS
	private static final ManagerConnection SINGLETON = new ManagerConnection();
	
	// rendo il costruttore privato in modo da avere una classe singoletto
	private ManagerConnection() {}
	
	static ManagerConnection getManagerConnection() {
		return SINGLETON;
	}
	
	void initConnection(String address, String port) throws IOException {
		InetAddress addr = InetAddress.getByName(address);
		socket = new Socket(addr, new Integer(port));
	}
	
	void openStreams() throws IOException {
		ou = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	void closeStreams() throws IOException {
		in.close();
		ou.close();
	}
	
	void closeConnection() throws IOException {
		in.close();
		ou.close();
		socket.close();
	}
	
	ObjectInputStream getInputStream() {
		return in;
	}
	
	ObjectOutputStream getOutputStream() {
		return ou;
	}
}
