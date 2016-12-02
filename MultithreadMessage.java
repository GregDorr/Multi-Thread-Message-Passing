import java.util.Scanner;

//class to hold the message
class ThreadMessage{
	public String string = "";
}


public class MultithreadMessage {
	
	//The Thread Message
	private static ThreadMessage m1;
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//initializing m1
		m1 = new ThreadMessage();
		
		/* A new thread to receive the message
		 * Lambda expression to send the thread 
		 * to the thread method
		*/
		Thread t1 = new Thread(()-> thread());
		
		//starting the message thread
		t1.start();
		
		//starting a loop to read the users input
		while(true){
			System.out.print("Please enter a message to be passed: ");
			String str = scan.nextLine();
			synchronized(m1){
				m1.string = str;
				m1.notifyAll();
			}
			
			/*waiting in order to give the second thread
			 * time to display the message before looping
			 * through again.
			*/
			try{
				Thread.sleep(10);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//where the second thread is waiting
	public static void thread(){
		while(true){
			try{
				/* m1 waits to be notified by the main thread
				 * Then outputs the string entered by the user.
				*/
				synchronized(m1){
					m1.wait();
					System.out.println("\nYou Entered: " + m1.string);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
