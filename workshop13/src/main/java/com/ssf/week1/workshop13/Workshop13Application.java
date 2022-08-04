package com.ssf.week1.workshop13;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssf.week1.workshop13.util.IOUtil;

/*
 * mvn spring-boot:run -Dspring-boot.run.arguments="--dataDir=<data directory>"
 */
@SpringBootApplication
public class Workshop13Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop13Application.class);
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Workshop13Application.class);

		// check arguments and call IOUtil createDir method
		DefaultApplicationArguments appOpts = new DefaultApplicationArguments(args);
		List<String> optsVals = appOpts.getOptionValues("dataDir");
		String dataDir;
		if (optsVals == null) {
			logger.info("You have not entered a directory path in arguments");
			System.exit(1);
		}
		
		dataDir = optsVals.get(0);
		IOUtil.createDir(dataDir);

		app.run(args);
	}

}
