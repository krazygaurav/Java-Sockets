import java.net.*;
import java.io.*;

class Sender implements Runnable
{
	DataInputStream input;
	DataOutputStream output;
	Thread thread;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Sender(DataInputStream input, DataOutputStream output){
		this.input = input;
		this.output = output;
		thread = new Thread(this, "Hello");
		thread.start();
	}
	public void run(){
		try
		{
			while(true){
				System.out.print("You: ");
				String str = br.readLine();
				output.writeUTF(str);
				thread.sleep(500);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
		}
	}
}

class Reciever implements Runnable
{
	DataInputStream input;
	DataOutputStream output;
	Thread thread;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Reciever(DataInputStream input, DataOutputStream output){
		this.input = input;
		this.output = output;
		thread = new Thread(this, "Hell");
		thread.start();
	}
	public void run(){
		try
		{
			while(true){
				String ans = input.readUTF();
				if(ans.equals("Bye"))
				{
					output.writeUTF("Bye");
					input.close();
					break;
				}
				System.out.println("Server: "+ ans);
				thread.sleep(500);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream input = new DataInputStream(client.getInputStream());
		DataOutputStream output = new DataOutputStream(client.getOutputStream());
	
		System.out.println(input.readUTF());
		output.writeUTF("Client is Online..");
		
		Sender sender = new Sender(input, output);
		Reciever reciever = new Reciever(input, output);
		
		//client.close();
	}
}