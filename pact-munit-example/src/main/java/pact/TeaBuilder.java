package pact;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;

public class TeaBuilder {
	private static DslPart pactResponseBody;
	
	public static DslPart getPactResponseBody() {
		return pactResponseBody;
	}
	
	public static DslPart getTea() {
		pactResponseBody = new PactDslJsonBody();
		
		((PactDslJsonBody) pactResponseBody)
			.stringMatcher("type", "/(caffinated|decaffinated)/")
			.integerType("supply")
			.integerType("cost");
		
		return pactResponseBody;
	}
}
