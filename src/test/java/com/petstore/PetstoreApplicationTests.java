package com.petstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class PetstoreApplicationTests {

	Logger log = Logger.getLogger(getClass().getName());

	@Value("${myname}")    //look for a property of my name and set the value of myname to
	String myname;		  // this variable

	@Test
	void contextLoads() {
	}

	@Test
	void printPropertyValue(){
		log.info(myname);
	}

}
