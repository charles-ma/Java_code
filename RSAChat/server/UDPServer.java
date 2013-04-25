/**
 * 
 */
package server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * UDP chatting server
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class UDPServer {

	/**
	 * Main function for server
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		rsa.Cipher cipher = new rsa.Cipher(); 
		rsa.Key priKey = new rsa.Key(3841, 4891);
		DatagramSocket serverSocket = null;
		if(args.length != 0) serverSocket = new DatagramSocket(Integer.parseInt(args[0])); 
		else serverSocket = new DatagramSocket(8000);
		System.out.println("Server start... waiting for client...");
		byte[] firstReceiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(firstReceiveData, firstReceiveData.length);
        serverSocket.receive(receivePacket);
        String sentence = new String(receivePacket.getData());
        System.out.println("Other: " + cipher.decrypt(sentence, priKey));
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        new client.UDPClient(IPAddress.getHostAddress(), port, serverSocket).startChatting();
	}

}
