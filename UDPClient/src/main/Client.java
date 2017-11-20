package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	public static void main(String[] args) throws Exception {
		
		DatagramSocket clientSocket = new DatagramSocket();
		
		BufferedReader consoleInputStream = new BufferedReader(new InputStreamReader(System.in));
				
		System.out.println("Enter a word or a sentence:");
		String userInput = consoleInputStream.readLine();
		
		byte[] sendData = userInput.getBytes();
		int portNumber = 12000;
		InetAddress address = InetAddress.getByName("localhost");		
		/* 
		 * Alternatively replace x.x.x.x with IP address of another device to connect to it instead of localhost
		 * InetAddress address = InetAddress.getByName("x.x.x.x");
		 */				
		DatagramPacket packetForServer = new DatagramPacket(sendData, sendData.length, address, portNumber);		
		clientSocket.send(packetForServer);
		
		byte[] receiveData = new byte[1024];
		DatagramPacket packetFromServer = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(packetFromServer);
		
		String result = new String(packetFromServer.getData()).trim(); //trims extra white space from buffer
		System.out.println("You entered: " + userInput + "\nServer replied: " + result);
		
		clientSocket.close();		
	}
}
