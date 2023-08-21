package pact;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;

public class PactService {
    private static Process pactApp = null;
	private static String pactServicePort = "5555";
	private static String mockPactServerPort = "4444";

	public static Process startPactService() throws IOException, InterruptedException {
        String command = "pact-jvm-server "
            + pactServicePort
            + " -l "
            + mockPactServerPort
            + " -u "
            + mockPactServerPort
            + " --broker "
            + brokerUrl
            + " -v 3";

        Process pactService = Runtime.getRuntime().exec(command);

        return pactService;
    }

public static void stopPactService() throws ClientProtocolException, IOException {
        pactService.destroy();
    }
}
