import java.io.*;
import java.net.*;

public class Client {

	public static int lastGuess = 0;
	public static int numberOfGuesses = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Connection established");
        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket(InetAddress.getLocalHost(),1234);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+ InetAddress.getLocalHost());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+InetAddress.getLocalHost());
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Protocol protocol=new Protocol();
        String fromServer;
        String fromUser;
		String res;
		
	
        while ((fromServer=in.readLine())!=null) {
			res=protocol.processInput(fromServer);
			out.println(res);
			
    if (fromServer.equals("Bye."))
	break;
        }

        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
		
    }

}
