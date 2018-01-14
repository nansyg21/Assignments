import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Protocol {
	
	
	private static final int SENDLOGIN = 0;
	private static final int VERIFYLOGIN = 1;
	private static final int GETNUMBER = 2;
	private static final int WAITING = 3;
	private static final int SENDGUESS = 4;
	private static final int GETSCORE = 5;
	private static final int GETMENU = 6;
	
	private int state = SENDLOGIN;
	
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	
	private String theOutput = null;
	
	
	
	public Protocol()
	{
	}
		public String processInput(String theInput) throws IOException {
			
			if(state == SENDLOGIN)
			{
				System.out.println("Select your username");
				String fromUser = stdIn.readLine();
				if (fromUser != null) {
		            theOutput=fromUser;
				}
				else
				{
					Random rand = new Random(System.currentTimeMillis()); 
					int value = rand.nextInt(1000);
					theOutput = "Guest"+ value;
				}
				state = GETMENU;
			}
			else if(state == GETMENU)
			{
				System.out.println("Ready for action?");
				System.out.println("1. Hit me with another number");
				//System.out.println("2. View scoreboard");
				System.out.println("3. Exit");
				//System.out.println("Ready for another number? (Y/N)");
				String fromUser = stdIn.readLine();
				if (fromUser != null) {
					
					while(!fromUser.equals("1")&&!fromUser.equals("2")&&!fromUser.equals("3"))
					{
						System.out.println("Ok let's try again");
						System.out.println("Type the number of the menu option you want");
						System.out.println("1. Hit me with another number");
						//System.out.println("2. View scoreboard");
						System.out.println("3. Exit");
						System.out.println("Then hit Enter");
						fromUser = stdIn.readLine();
					}
					if(fromUser.equals("1"))
					{
						theOutput = fromUser;
						state = GETNUMBER;
					}
					else if(fromUser.equals("3"))
					{
						theOutput = fromUser;
						System.out.println("Come back at any time! See you!!!");
						System.exit(0);
					}
					//else if(fromUser.equals("2")){
					//	theOutput = fromUser;
					//	state = GETSCORE;
					//}
					//else if(fromUser.equals("N")||fromUser.equals("n"))
					//{
					//	theOutput = fromUser;
					//	System.out.println("Come back at any time! See you!!!");
					//	System.exit(0);
					//}					
					
		            //System.out.println("Client: " + fromUser);
		            //theOutput=fromUser;
				}
			}
			else if(state == GETNUMBER)
			{
				System.out.println("You got a new number you can start guessing (numbers are between 1 and 1000)");
				String fromUser = stdIn.readLine();
				
				while((fromUser==null)||(!isNumeric(fromUser)))
				{
					System.out.println("Please insert a numeric value");
					fromUser = stdIn.readLine();
				}
				
				if (fromUser != null) {
		            theOutput=fromUser;
				}
				state = WAITING;
			}
			/*else if(state == GETSCORE)
			{
				System.out.println("Scoreboard for this server instance:");
				
				System.out.println(theInput);
				
				System.out.println("Ready for action?");
				System.out.println("1. Hit me with another number");
				//System.out.println("2. View scoreboard");
				System.out.println("3. Exit");
				String fromUser = stdIn.readLine();
				if (fromUser != null) {
					
					while(!fromUser.equals("1")&&!fromUser.equals("2")&&!fromUser.equals("3"))
					{
						System.out.println("Ok let's try again");
						System.out.println("Type the number of the menu option you want");
						System.out.println("1. Hit me with another number");
						//System.out.println("2. View scoreboard");
						System.out.println("3. Exit");
						System.out.println("Then hit Enter");
						fromUser = stdIn.readLine();
					}
					if(fromUser.equals("1"))
					{
						theOutput = fromUser;
						state = GETNUMBER;
					}
					//else if(fromUser.equals("2")){
					//	theOutput = fromUser;
					//	state = GETSCORE;
					//}
					else if(fromUser.equals("3"))
					{
						theOutput = fromUser;
						System.out.println("Come back at any time! See you!!!");
						System.exit(0);
					}	
				}
			}*/
			else if(state == WAITING)
			{
				String[] resultInput = theInput.split("\\s+");
				if(resultInput[0].equals("Correct!"))
				{
					System.out.println("Nice guess! "+"Number "+resultInput[1] + " is correct and it only took you "+resultInput[2]+" guesses");
					System.out.println("Ready for action?");
					System.out.println("1. Hit me with another number");
					//System.out.println("2. View scoreboard");
					System.out.println("3. Exit");
					//System.out.println("Ready for another number? (Y/N)");
					String fromUser = stdIn.readLine();
					if (fromUser != null) {
						
						while(!fromUser.equals("1")&&!fromUser.equals("2")&&!fromUser.equals("3"))
						{
							System.out.println("Ok let's try again");
							System.out.println("Type the number of the menu option you want");
							System.out.println("1. Hit me with another number");
							//System.out.println("2. View scoreboard");
							System.out.println("3. Exit");
							System.out.println("Then hit Enter");
							fromUser = stdIn.readLine();
						}
						if(fromUser.equals("1"))
						{
							theOutput = fromUser;
							state = GETNUMBER;
						}
						//else if(fromUser.equals("2")){
						//	theOutput = fromUser;
						//	state = GETSCORE;
						//}
						else if(fromUser.equals("3"))
						{
							theOutput = fromUser;
							System.out.println("Come back at any time! See you!!!");
							System.exit(0);
						}					
						
			            //System.out.println("Client: " + fromUser);
			            //theOutput=fromUser;
					}
				}
				else if(theInput.equals("Higher"))
				{
					System.out.println("Nope! Let's try a lower number");
					String fromUser = stdIn.readLine();
					
					while((fromUser==null)||(!isNumeric(fromUser)))
					{
						System.out.println("Please insert a numeric value");
						fromUser = stdIn.readLine();
					}
					
					if (fromUser != null) {
			            //System.out.println("Client: " + fromUser);
			            theOutput=fromUser;
					}
					state = WAITING;
				}
				else if(theInput.equals("Lower"))
				{
					System.out.println("Nope! Let's try a higher number");
					String fromUser = stdIn.readLine();
					
					while((fromUser==null)||(!isNumeric(fromUser)))
					{
						System.out.println("Please insert a numeric value");
						fromUser = stdIn.readLine();
					}
					
					if (fromUser != null) {
			            //System.out.println("Client: " + fromUser);
			            theOutput=fromUser;
					}
					state = WAITING;
				}
			}
	        
			
			
			
				return theOutput;
		}
		
		public static boolean isNumeric(String str)
		{
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}
	

}
