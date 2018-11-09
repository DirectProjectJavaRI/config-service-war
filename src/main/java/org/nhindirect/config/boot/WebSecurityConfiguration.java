package org.nhindirect.config.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
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
    
	
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() 
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String encodedPassword = passwordEncoder().encode(password);
        manager.createUser(User.withUsername(username).password(encodedPassword).roles("USER").build());
        return manager;
    } 	
	
    
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }      
}