package pact;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class PactMockServer {
    private static String pactServicePort = "5555";
	private static String mockPactServerPort = "4444";
	private static CloseableHttpClient client = null;
	
	public static void close() throws IOException {
		client.close();
	}

    public static void createPactMockServer(String pactBody) throws ClientProtocolException, IOException, InterruptedException {
    client = HttpClients.createDefault();
    HttpPost post = new HttpPost("http://localhost:" + pactServicePort + "/create?state=NoUsers&path=/sub/ref/path");
    ResponseHandler<String> handler = new BasicResponseHandler();

    post.setHeader("Content-Type", "application/json");
    post.setEntity(new StringEntity(pactBody));

    client.execute(post, handler);
    
    System.out.println("Run the Pact Mock Server.");

    close();
    }

    public static void writePactFile() throws ClientProtocolException, IOException {
		client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:" + pactServicePort + "/complete");
		JSONObject requestBody = new JSONObject();
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		requestBody.put("port", mockPactServerPort);
		
		post.setHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(requestBody.toString()));
		
		client.execute(post, handler);
	    	    
	    close();
	}
}
