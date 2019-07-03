package com.visteoncloud.tusc.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler implements RequestHandler<Request, Response>  {

	public Response handleRequest(Request input, Context context) {
		
		LambdaLogger logger = context.getLogger();
		logger.log("received person: " + input.getFirstName());

		return new Response("Request received");
		
	}

}
