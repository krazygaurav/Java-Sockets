import java.io.*;
class Thread3
{
	Thread3(){

		new Thread(){
			public void run(){
				try
				{
					for(int i=10;i<20;i++){
						System.out.println(i);
					Thread.sleep(1000);
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}.start();

		new Thread(new Runnable(){
			public void run(){
				try
				{
					for(int i=20;i<30;i++){
						System.out.println(i);
					Thread.sleep(600);
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	static void createThread(String str){
		new Thread(new Runnable(){
			public void run(){
				try
				{
					for(int i=20;i<30;i++){
						System.out.println(Thread.currentThread().getName()+""+i);
					Thread.sleep(1000);
					}
					System.out.println(Thread.currentThread().getName()+" is exiting..\n");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		},str).start();
	}
	public static void main(String[] args){
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		new Thread(){
			public void run(){
				try
				{
					int i=0;
					while(true){
						System.out.print("\nStart new Thread[y/n]: ");
						String str = br.readLine();
						if(str.equals("y"))
							createThread("Thread"+(i++)+": ");
						Thread.sleep(100);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
}
	