import java.net.*;
import java.io.*;
class Chat implements Runnable
{
	DataInputStream input;
	DataOutputStream output;
	Thread thread;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Chat(DataInputStream input, DataOutputStream output){
		this.input = input;
		this.output = output;
		thread = new Thread(this, "Hello");
		thread.start();
	}
	public void run(){
		try
		{
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
		}
	}
}
class ServerThread{
	public static void main(String args[]) throws IOException{
		ServerSocket serverSocket = new ServerSocket(1234);
		Socket clientSocket = serverSocket.accept();
		
		//creating input stream of Client
		InputStream is = clientSocket.getInputStream();
		DataInputStream input = new DataInputStream(is);
		
		//creting output stream of client
		OutputStream os = clientSocket.getOutputStream();
		DataOutputStream output = new DataOutputStream(os);

		
		output.writeUTF("Server is online..");

		Chat chat = new Chat(input, output);				
		//clientSocket.close();
	}
}