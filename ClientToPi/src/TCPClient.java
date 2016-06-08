import java.io.*;
import java.net.*;

public class TCPClient {

	static boolean exit = false;
	
	public static void sendMessage() throws Exception{
		String output;
		String returnedString = null;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("pi09.fu.campus",6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		clientSocket.setKeepAlive(true);
		output = inFromUser.readLine();
		if(output.toString().equals("exit")){
			exit = true;
		}
		outToServer.writeBytes(output + '\n');
		try{
			returnedString = inFromServer.readLine();
			returnedString = returnedString.replaceAll("/", "\n");
			System.out.println("Server: " + returnedString);
		}
		catch(Exception e){
			System.out.println("received no response from master node");
		}
		clientSocket.close();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//First Change from mac2
		//First change from pc this is cool mhmm
		try{
			while(exit!=true){
				sendMessage();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
