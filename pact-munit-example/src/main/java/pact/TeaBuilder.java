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
				.minArrayLike("tea", 1)
						.stringType("name", "mint")
						.stringMatcher("type", "caffeinated|decaffeinated")
						.integerType("supply")
						.stringMatcher("cost", "\\d+\\.?\\d*GBP")
					.closeObject()
				.closeArray();
		
		return "";
	}
}
