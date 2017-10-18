import java.net.*;
import java.io.*;
class Server{
	public static void main(String args[]) throws IOException{
		ServerSocket serverSocket = new ServerSocket(1234);
		Socket clientSocket = serverSocket.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//creating input stream of Client
		InputStream is = clientSocket.getInputStream();
		DataInputStream input = new DataInputStream(is);
		
		//creting output stream of client
		OutputStream os = clientSocket.getOutputStream();
		DataOutputStream output = new DataOutputStream(os);
		/*
		//output.writeBytes("Sun Raha hu Mein..\n");
		output.writeUTF("Server: Sun Raha hu Mein..");
		output.writeUTF("Server: Bol: ");
		
		//getting "bola" from client
 		String bola = input.readUTF();
		System.out.print("Client: "+bola)'
		
		System.out.print(input.readUTF());
		String meneBola = br.readLine();
		output.writeUTF(meneBola);
		
		*/
		output.writeUTF("Server is online..");
		System.out.println(input.readUTF());
		while(true){
			String ans = input.readUTF();
			System.out.println("Client: "+ans);
			if(ans.equalsIgnoreCase("Bye"))
			{
				output.writeUTF("Bye");
				output.close();
				break;
			}
			
			System.out.print("You: ");
			String str = br.readLine();
			output.writeUTF(str);
		}
		
		clientSocket.close();
	}
}