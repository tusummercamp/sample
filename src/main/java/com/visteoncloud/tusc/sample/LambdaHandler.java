package com.visteoncloud.tusc.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class LambdaHandler {
	
	public String sampleHandler(int sampleCount, Context context) {
		
		LambdaLogger logger = context.getLogger();
		logger.log("received: " + sampleCount);

		return String.valueOf(sampleCount);
		
	}

}
