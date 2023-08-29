package pact;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;

public class PactService {
    private static Process pactService = null;
	private static String pactServicePort = "5555";
	private static String mockPactServerPort = "4444";
	
	private static void printBuffer(InputStream buff) throws IOException {
		if(buff.available() != 0) {
			String input = "";
			int max = 280;
			if(buff.available() <= max) {
				max = buff.available();
			}
			
			byte[] bError = new byte[max];
			buff.read(bError, 0, max);
			
			for(byte b : bError) {
				char ch = (char)b;
				input += ch;
			}
			
		    System.out.println(input);
		}
	}
	
	private static void printServiceResponse() throws IOException, InterruptedException {
		InputStream buff = pactService.getInputStream();
		long startTime = System.currentTimeMillis();
		long timePassed;
		do {
			Thread.sleep(200);
			timePassed = System.currentTimeMillis() - startTime;
			buff = pactService.getInputStream();
			
		} while (!(buff.available() != 0 || timePassed > 2000));
		printBuffer(buff);
	}

	public static Process startPactService() throws IOException, InterruptedException {
        String command = "sh " 
			+ System.getProperty("user.dir") 
			+ "/pact-jvm-server-4.1.42/bin/pact-jvm-server "
            + pactServicePort
            + " -l "
            + mockPactServerPort
            + " -u "
            + mockPactServerPort
            + " -v 3";
		
        pactService = Runtime.getRuntime().exec(command);
		printServiceResponse();

        return pactService;
    }

public static void stopPactService() throws ClientProtocolException, IOException {
        pactService.destroy();
    }
}
