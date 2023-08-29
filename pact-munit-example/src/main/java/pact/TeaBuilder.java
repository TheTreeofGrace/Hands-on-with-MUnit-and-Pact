package pact;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;

public class TeaBuilder {
	private static DslPart providerResponseBody;
	
	public static DslPart getPactResponseBody() {
		return providerResponseBody;
	}
	
	public static String getTea() {
		providerResponseBody = new PactDslJsonBody();
		
		((PactDslJsonBody) providerResponseBody)
				.array("tea")
					.object()
						.stringType("name", "mint")
						.stringMatcher("type", "(caffinated|decaffinated)")
						.integerType("supply")
						.integerType("cost")
					.closeObject()
				.closeArray();
		
		return "";
	}
}
