import java.net.*;
import java.io.*;

class Client{
	public static void main(String args[]) throws IOException{
		String ip="";
		if(args.length == 1)
			ip = args[0];
		else 
		{
			System.out.println("Usage:[ServerIPAddress]");
			System.exit(1);
		}
		Socket client = new Socket(ip, 1234);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream input = new DataInputStream(client.getInputStream());
		DataOutputStream output = new DataOutputStream(client.getOutputStream());
		
		/*
		//read from server
		System.out.println(input.readUTF());
		System.out.println(input.readUTF());
		
		//write to server
		String bola = br.readLine();
		output.writeUTF(bola);
		
		//getting ans from server
		System.out.println(input.readUTF());
		
		//getting input from Server
		output.writeUTF("client: Say Something.. ");
		System.out.print(input.readUTF());*/
		
		System.out.println(input.readUTF());
		output.writeUTF("Client is Online..");
		while(true){
			System.out.print("You: ");
			String str = br.readLine();
			output.writeUTF(str);
			String ans = input.readUTF();
			if(ans.equals("Bye"))
			{
				output.writeUTF("Bye");
				input.close();
				break;
			}
			System.out.println("Server: "+ ans);
		}
		
		
		client.close();
	}
}