import java.io.*;
import java.net.*;
import java.util.*;
class Talk extends Thread{
	Socket client;
	DataInputStream input;
	DataOutputStream output;
	String name;
	BufferedReader br;
	Server s;
	Talk(Server s, Socket client, String name) throws IOException{
		this.client = client;
		this.name = name;
		this.s = s;
		br = new BufferedReader(new InputStreamReader(System.in));
		input = new DataInputStream(client.getInputStream());
		output = new DataOutputStream(client.getOutputStream());
		startTalking();
	}
	void startTalking(){
		//read
		start();
		//reply
		//reply();
		
	}
	public void run(){
		try{
			while(true){
			String str = name + input.readUTF();
			if(str.equals("bye")){
				System.out.println(name+"Bye");
				break;
			}
			//System.out.println(str);
			s.replyToAll(str, output);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}		
}
public class Server{
	Hashtable streams;
	int i=1;

	Server(){
		try{
			ServerSocket server = new ServerSocket(1234);
			streams = new Hashtable();
			while(true){
				try{
					String s = "Client"+(i++)+":";
					Socket socket = server.accept();
					System.out.println(socket);
					streams.put(socket, new DataOutputStream(socket.getOutputStream()));
					System.out.println("here");
					new Talk(this, socket, s);
					//Thread.sleep(500);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}	
		}
		catch (IOException e){
			e.printStackTrace();
		}		
	}

	public static void main(String args[]) throws IOException{
		new Server();		
	}

	Enumeration getOutputStreams(){
		return streams.elements();
	}

	void replyToAll(String msg, DataOutputStream myStream){
		//synchronized(streams)
		{
			for(Enumeration e=getOutputStreams(); e.hasMoreElements(); ){
				DataOutputStream o = (DataOutputStream)e.nextElement();
				if(o != myStream){
					try{
						o.writeUTF(msg);	
					}
					catch (IOException ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}
}