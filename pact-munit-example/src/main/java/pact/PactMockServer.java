package pact;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class PactMockServer {
    private static String pactServicePort = "5555";
	private static String mockPactServerPort = "4444";
	private static CloseableHttpClient client = null;

    public static void createPactMockServer(String pactBody) throws ClientProtocolException, IOException, InterruptedException {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost("http://localhost:" + pactServicePort + "/create?state=NoUsers&path=/sub/ref/path");
    ResponseHandler<String> handler = new BasicResponseHandler();

    post.setHeader("Content-Type", "application/json");
    post.setEntity(new StringEntity(pactBody));

    client.execute(post, handler);

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
	
	public static void publishPactFile(String consumerPactArguments) throws ClientProtocolException {
			client = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://localhost:" + pactServicePort + "/publish");
			JSONObject pactArgs = new JSONObject(consumerPactArguments);
			JSONObject requestBody = new JSONObject();
			ResponseHandler<String> handler = new BasicResponseHandler();
	
			requestBody.put("consumer", (String) pactArgs.get("consumer"));
			requestBody.put("consumerVersion", (String) pactArgs.get("consumerVersion"));
			requestBody.put("provider", (String) pactArgs.get("provider"));
			
			post.setHeader("Content-Type", "application/json");
			
			try {
				post.setEntity(new StringEntity(requestBody.toString()));
				String body = client.execute(post, handler);
			    	    
			    close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	    
	}
	
	public static void close() throws IOException {
		client.close();
	}
}
