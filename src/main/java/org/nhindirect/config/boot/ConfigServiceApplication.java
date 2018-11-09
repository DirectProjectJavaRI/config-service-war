package org.nhindirect.config.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"org.nhindirect.config", "org.nhindirect.config.boot"})
@SpringBootApplication
public class ConfigServiceApplication extends SpringBootServletInitializer
{	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
	    return application.sources(ConfigServiceApplication.class);
	}	
	
}
