package com.ssf.week1.workshop11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Workshop11Application {
	private static String DEFAULT_PORT = "3000";
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);

	@Bean
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter loggerFilter = new CommonsRequestLoggingFilter();
		loggerFilter.setIncludeClientInfo(true);
		loggerFilter.setIncludeHeaders(true);
		loggerFilter.setIncludeQueryString(true);
		return loggerFilter;
	}

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Workshop11Application.class);
		DefaultApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		List<String> optsVals = cliOpts.getOptionValues("port");
		logger.info("optsVals > " + optsVals);

		// is --port set?
		String port = null;
		if (optsVals == null || optsVals.get(0) == null) {
			port = System.getenv("PORT");
			if (port == null) {
				port = DEFAULT_PORT;
			}
		} else {
			port = String.valueOf(optsVals.get(0));
		}

		logger.info("PORT > " + port);

		if (port != null)
			app.setDefaultProperties(Collections.singletonMap("server.port", port));

		app.run(args);
	}

}
