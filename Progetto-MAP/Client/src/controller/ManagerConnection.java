package controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

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
		openStreams();
	}
	
	void openStreams() throws IOException {
		ou = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	void closeStreams() throws IOException {
		ou.close();
		in.close();
	}
	
	void closeConnection() throws IOException {
		closeStreams();
		socket.close();
	}
	
	ObjectInputStream getInputStream() {
		return in;
	}
	
	ObjectOutputStream getOutputStream() {
		return ou;
	}
	
	void ServerComunication(int op, float sup, float rate, String target, String back) throws IOException{
		openStreams();
		getOutputStream().writeObject(op);
		getOutputStream().writeObject(sup);
		getOutputStream().writeObject(rate);
		getOutputStream().writeObject(target);
		getOutputStream().writeObject(back);
	}
}
