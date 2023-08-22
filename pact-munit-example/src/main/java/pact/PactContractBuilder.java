package pact;

public class PactContractBuilder {
    private RequestResponsePact pact;
	
	public PactConsumerBuilder(String pactArgs, String endpoint, String pactRequestBody, DslPart pactResponseBody) {
		JSONObject pactSetup = new JSONObject(pactArgs);
		JSONObject requestBody = new JSONObject();
		
		pact = ConsumerPactBuilder
				.consumer((String) pactSetup.get("consumer"))
				.hasPactWith((String) pactSetup.get("provider"))
				.uponReceiving((String) pactSetup.get("uponReceiving"))
			    .path(endpoint)
			    .method((String) pactSetup.get("method"))
			    .headers("Content-Type", "application/json")
			    .body(new JSONObject(pactRequestBody))
			    .willRespondWith()
			    .status((Integer) pactSetup.get("status"))
			    .body(pactResponseBody)
			    .toPact();
	}
	
	private List<JSONObject> interactionsBuilder() {
		List<RequestResponseInteraction> pactInteractions = pact.getInteractions();
		List<JSONObject> interactions = new ArrayList<JSONObject>();
		
		for (RequestResponseInteraction interaction : pactInteractions) {
			String method = interaction.getRequest().getMethod();
			
			if((method.contains("GET") == true) || (method.contains("DELETE") == true)) {
				Map <String, List<String>> headers = new HashMap<String, List<String>>();
				interaction.getRequest().setHeaders(headers);
				interaction.getRequest().setBody(OptionalBody.missing());
			}
			
			Map<String, Object> pactV3Interaction = interaction.toMap(PactSpecVersion.V3);
			JSONObject pactV3Interactions = new JSONObject(pactV3Interaction);
			JSONObject requestBody = new JSONObject();
			
			if(!interaction.getRequest().getBody().isMissing()) {
				requestBody = new JSONObject(interaction.getRequest().getBody().valueAsString());
				((JSONObject)pactV3Interactions.get("request")).put("body", requestBody);
			}
			
			JSONObject responseBody = new JSONObject(interaction.getResponse().getBody().valueAsString());
			((JSONObject)pactV3Interactions.get("response")).put("body", responseBody);
			
			interactions.add(pactV3Interactions);
		}
		
		return interactions;
	}
	
	public String pactBuilder() {
		JSONObject consumer = new JSONObject();
		JSONObject provider = new JSONObject();
		JSONObject pactRequest = new JSONObject();
		List<JSONObject> interactions = interactionsBuilder();
		
		consumer.put("name", pact.getConsumer().getName());
		provider.put("name", pact.getProvider().getName());
		
		pactRequest.put("consumer", consumer);
		pactRequest.put("provider", provider);
		pactRequest.put("interactions", interactions);
		
		return pactRequest.toString();
	}
}
