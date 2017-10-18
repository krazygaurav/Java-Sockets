import java.net.*;
import java.io.*;
class Sender implements Runnable{
	BufferedReader br;
	Socket client;
	DataOutputStream output;
	Thread thread;
	Sender(Socket client) throws IOException{
		this.client = client;
		br = new BufferedReader(new InputStreamReader(System.in));
		output = new DataOutputStream(client.getOutputStream());
		thread = new Thread(this, "Client1");
		thread.start();
	}
	public void run(){
		try
		{
			while(true){
				System.out.print("You: ");
				String str = br.readLine();
				output.writeUTF(str);
				if(str.equals("bye"))
					break;
				Thread.sleep(400);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
class Reciever implements Runnable{
	Socket client;
	DataInputStream input;
	Thread thread;
	Reciever(Socket client) throws IOException{
		this.client = client;
		input = new DataInputStream(client.getInputStream());
		thread = new Thread(this, "Client1");
		thread.start();
	}
	public void run(){
		try
		{
			while(true){
				String str = "Server: " + input.readUTF();
				if(str.equals("bye")){
					System.out.println("Client: Bye");
					break;
				}
				System.out.println(str);
				Thread.sleep(600);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
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

		Sender sender = new Sender(client);
		Reciever reciever = new Reciever(client);
	}
}
		