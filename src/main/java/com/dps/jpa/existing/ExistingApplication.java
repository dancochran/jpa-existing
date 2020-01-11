package com.dps.jpa.existing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExistingApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ExistingApplication.class, args);
	}
	
	// DTOs (and general strat for response holder classes for marshaling to json
	// Actuator customs
	// Reactive for async/streams
	// finish repository with relationships and cascades
	// grpc

}
