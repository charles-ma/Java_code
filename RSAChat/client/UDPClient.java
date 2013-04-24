/**
 * 
 */
package client;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * UDP chatting client
 * @author machao
 * @version 1.0
 */
public class UDPClient {

	private InetAddress ip = null;
	private int port = 0;
	private DatagramSocket clientSocket= new DatagramSocket();
	private byte[] receiveData = new byte[1024];
	private byte[] sendData = new byte[1024];
	
	/**
	 * Main function for client
	 * @param args first parameter is the server name, second is the server port
	 */
	public static void main(String[] args) throws Exception{
		
		if(args.length < 2) {
			System.out.println("Connecting to Server 127.0.0.1 @ port 8000");
			new UDPClient("localhost", 8000).startChatting();
		} else {
			int portNum = Integer.parseInt(args[1]);
			System.out.println("Connecting to Server " + args[0] + " @ port " + args[1]);
			new UDPClient(args[0], portNum).startChatting();
		}
	}

	/**
	 * Constructor for UDPClient
	 * @param hostName host name or ip
	 * @param port host port
	 * @throws Exception can't connect to the host
	 */
	public UDPClient(String hostName, int port) throws Exception{
		this.ip = InetAddress.getByName(hostName);
		this.port = port;
	}
	
	/**
	 * Another constructor for UDPClient
	 * @param ip host name or ip
	 * @param port host port
	 * @param socket existing socket
	 * @throws Exception can't connect to the host
	 */
	public UDPClient(String hostName, int port, DatagramSocket socket) throws Exception{
		this.ip = InetAddress.getByName(hostName);
		this.port = port;
		this.clientSocket = socket;
	}
	
	/**
	 * Start the chatting
	 * @throws Exception thread error
	 */
	public void startChatting() throws Exception {
		ReadThread rt = new ReadThread();
		WriteThread wt = new WriteThread();
		Thread r = new Thread(rt);
		Thread w = new Thread(wt);
		r.start();
		w.start();
		r.join();
		w.join();
	}
	
	/**
	 * Read thread
	 * @author machao
	 * @version 1.0
	 */
	class ReadThread implements Runnable {

		@Override
		public void run() {
			while(true) {
				Arrays.fill(receiveData, (byte)0);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try {
					clientSocket.receive(receivePacket);
				} catch(Exception e) {
					System.out.println("Data receiving error!");
					return;
				}
				String response = new String(receivePacket.getData());
				System.out.println("Other: " + response);
			}
		}
		
	}
	
	/**
	 * Write thread
	 * @author machao
	 * @version 1.0
	 */
	class WriteThread implements Runnable {

		@Override
		public void run() {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			String userInput = "";
			try {
				userInput = inFromUser.readLine();
			} catch(Exception e) {
				System.out.println("Input error!");
				return;
			}
			while(!userInput.equals(".bye")) {
				Arrays.fill(sendData, (byte)0);
				sendData = userInput.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
				try {
					clientSocket.send(sendPacket);
				} catch(Exception e) {
					System.out.println("Data sending error!");
					return;
				}
				try {
					userInput = inFromUser.readLine();
				} catch(Exception e) {
					System.out.println("Input error!");
					return;
				}
			}
			System.exit(0);
		}
	}
	
}
