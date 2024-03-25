package com.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,
securedEnabled = true)
public class MySecConfig{
	
	
	@Bean
	 UserDetailsService detailsService(PasswordEncoder encoder) {
		 UserDetails userDetails = User.withUsername("user")
				 .password(encoder.encode("123")).roles("USER").build();
		 
		 UserDetails admin = User.withUsername("admin")
				 .password(encoder.encode("456")).roles("ADMIN","dev").build();
		 
		 UserDetails master = User.withUsername("master")
				 .password(encoder.encode("789")).roles("MASTER").build();
		 
	   return new InMemoryUserDetailsManager(userDetails,admin,master);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("emp/home").permitAll()
//				.requestMatchers("emp/**").hasRole("MASTER")
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	
	private Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaults() {
		// TODO Auto-generated method stub
		return Customizer.withDefaults();
	}


	@Bean
	 PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	

}
