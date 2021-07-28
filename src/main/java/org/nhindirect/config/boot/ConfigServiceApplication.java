package org.nhindirect.config.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan({"org.nhindirect.config"})
@SpringBootApplication
@EnableR2dbcRepositories("org.nhindirect.config.repository")
@EnableScheduling
public class ConfigServiceApplication extends SpringBootServletInitializer
{	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
	    return application.sources(ConfigServiceApplication.class);
	}	
	
}
