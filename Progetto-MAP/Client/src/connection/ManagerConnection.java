package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class ManagerConnection {
	private Socket socket;
	private ObjectOutputStream ou;
	private ObjectInputStream in;
	
	// SINGLETON CLASS
	private static final ManagerConnection SINGLETON = new ManagerConnection();
	
	// rendo il costruttore privato in modo da avere una classe singoletto
	private ManagerConnection() {}
	
	public static ManagerConnection getManagerConnection() {
		return SINGLETON;
	}
	
	public void initConnection(String address, String port) throws IOException {
		InetAddress addr = InetAddress.getByName(address);
		socket = new Socket(addr, new Integer(port));
		openStreams();
	}
	
	private void openStreams() throws IOException {
		ou = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	private void closeStreams() throws IOException {
		ou.close();
		in.close();
	}
	
	public void closeConnection() throws IOException {
		closeStreams();
		socket.close();
	}
	
	public ObjectInputStream getInputStream() {
		return in;
	}
	
	public ObjectOutputStream getOutputStream() {
		return ou;
	}
	
	public void ServerComunication(int op, float sup, float rate, String target, String back) throws IOException{
		getOutputStream().writeObject(op);
		getOutputStream().writeObject(sup);
		getOutputStream().writeObject(rate);
		getOutputStream().writeObject(target);
		getOutputStream().writeObject(back);
	}
	
	public String readString() throws IOException, ClassNotFoundException {
		return (String) in.readObject();
	}
}
