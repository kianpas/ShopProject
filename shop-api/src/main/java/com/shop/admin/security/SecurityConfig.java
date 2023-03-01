package com.shop.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService UserDetailService(){
		return new ShopmeUserDetailsService();
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	@Bean
	public DaoAuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(UserDetailService());
		authProvider.setPasswordEncoder(PasswordEncoder());
		
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers("/api/category/**").hasAnyAuthority("Admin", "Editor")
//		.antMatchers("/api/users/**")
///				.hasAuthority("Admin")
//		.anyRequest()
//		.authenticated()
		.and()
				.csrf().disable()
				.formLogin()
//		.loginPage("/login")
//				.failureUrl("/loginError")
//		.usernameParameter("email")
		.permitAll()
				.and()
				.rememberMe()
				.key("uniqueAndSecret")
				.tokenValiditySeconds(7 * 24 * 60 * 60)
		.and()
		.logout().permitAll();

		http.authenticationProvider(AuthenticationProvider());
		http.headers().frameOptions().sameOrigin();

		return http.build();
		
	}
	
//	 @Bean
//	 public WebSecurityCustomizer webSecurityCustomizer() {
//	    return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//	 }
	
	
	
	
	
}
