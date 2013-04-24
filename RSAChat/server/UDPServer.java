/**
 * 
 */
package server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * @author machao
 *
 */
public class UDPServer {

	/**
	 * Main function for server
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(8000);
		System.out.println("Server start... waiting for client...");
		byte[] firstReceiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(firstReceiveData, firstReceiveData.length);
        serverSocket.receive(receivePacket);
        String sentence = new String(receivePacket.getData());
        System.out.println("Other: " + sentence);
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        new client.UDPClient(IPAddress.getHostAddress(), port, serverSocket).startChatting();
	}

}
