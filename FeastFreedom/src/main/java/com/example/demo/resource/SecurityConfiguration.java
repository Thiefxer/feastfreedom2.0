package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@org.springframework.core.annotation.Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	KitchenService kService;

	// providing access to some type of urls for reg ,login, css, jss,html images
	// etc
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/kitchenlogin", "/userlogin","/new","/kitchen_registration", "/registration**", "/js/**", "/css/**",
						"/img/**", "/webjars/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/kitchenlogin")
				.loginProcessingUrl("/kitchen_setting")
				.defaultSuccessUrl("/kitchen_settings")

				.permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").permitAll();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authencationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		DaoAuthenticationProvider auth2 = new DaoAuthenticationProvider();
		auth.setUserDetailsService(kService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authencationProvider());
	}
}
