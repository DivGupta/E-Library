package com.fdm.frontEndFuntionality;

public class FunctionalityFactory {
	public static AgentCheck getAgent() {
		return new AgentCheck();
	}

	public static PublisherFunctionality getPublisher() {
		return new PublisherFunctionality();
	}

	public static UserFunctionality getUser() {
		return new UserFunctionality();
	}

}
