import java.net.*;
import java.io.*;
class Sender implements Runnable{
	BufferedReader br;
	Socket client;
	DataOutputStream output;
	Thread thread;
	String name;
	Sender(Socket client, String name) throws IOException{
		this.client = client;
		this.name = name;
		br = new BufferedReader(new InputStreamReader(System.in));
		output = new DataOutputStream(client.getOutputStream());
		thread = new Thread(this, name);
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
				//Thread.sleep(400);
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
	String name;
	Reciever(Socket client, String name) throws IOException{
		this.client = client;
		this.name = name;
		input = new DataInputStream(client.getInputStream());
		thread = new Thread(this, name);
		thread.start();
	}
	public void run(){
		try
		{
			while(true){
				String str = name + input.readUTF();
				if(str.equals("bye")){
					System.out.println(name+"Bye");
					break;
				}
				System.out.println(str);
				//Thread.sleep(600);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
class Server{
	public static void main(String args[]) throws IOException{
		new Thread(){
			public void run(){
				int i=1;
				try
				{
					ServerSocket server = new ServerSocket(1234);
					while(true){
						try
						{
							String s = "Client"+(i++)+": ";
							Socket socket = server.accept();
							new Reciever(socket, s);
							//new Sender(socket, s);
							Thread.sleep(500);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}	
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				
			}
		}.start();
	}
}