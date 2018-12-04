package org.nhindirect.config.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{	
	private static final String BCRYPT_PREFIX = "{bcrypt}";
	
	@Value("${spring.security.user.name}")
	protected String username;
	
	@Value("${spring.security.user.password}")
	protected String password;	
		
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception 
    {
    	httpSecurity
        .authorizeRequests()
            .antMatchers("/**").authenticated()
        .and()
            .httpBasic();
    	httpSecurity.csrf().disable();
    }   
    
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
    	if (password.startsWith(BCRYPT_PREFIX))
    		password = password.substring(BCRYPT_PREFIX.length());  		
    	else
    		password = passwordEncoder().encode(password);

        auth
           .inMemoryAuthentication()
           .passwordEncoder(passwordEncoder())
           .withUser(username)
           .password(password)
           .roles("USER");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }      
}