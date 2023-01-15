package com.example.dentalClinic.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private CustomeLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
			auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http)throws Exception
	{

		http
		.csrf()
		.disable()
		.httpBasic()
		.disable()
		.authorizeRequests()
		.antMatchers("/patient/Register").permitAll()
		.antMatchers("/patient/login").permitAll()
		.antMatchers("/doctor/Register").permitAll()
		.antMatchers("/doctor/login").permitAll()
		.antMatchers("/doctor/update").hasAuthority("doctor")
		.antMatchers("/doctor/AvailableTime").hasAuthority("doctor")
		.antMatchers("/doctor/bookedTimeline").hasAuthority("doctor")
		.antMatchers("/doctor/appointment").hasAuthority("doctor")
		.antMatchers("/doctor/cancelAppointment").hasAuthority("doctor")
		.antMatchers("/doctor/checkAppointment").hasAuthority("doctor")
		.antMatchers("/doctor/CreateCsvFile").hasAuthority("doctor")
		.antMatchers("/doctor/visitedCount").hasAuthority("doctor")
		.antMatchers("/doctor/getAllPatients").hasAuthority("doctor")
		.antMatchers("/doctor/getPatientsById").hasAuthority("doctor")
		.antMatchers("/patient/update").hasAuthority("patient")
		.antMatchers("/patient/listOfDoctors").hasAuthority("patient")
		.antMatchers("/patient/AvailabeDoctors").hasAuthority("patient")
		.antMatchers("/patient/Appointment").hasAuthority("patient")
		.antMatchers("/patient/cancelAppointment").hasAuthority("patient")
		.antMatchers("/patient/CreateCsvFile").hasAuthority("patient")
//		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler())
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler())
		.and()
		.httpBasic()
		.and()
		.logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(logoutSuccessHandler)
        .permitAll();
		return http.build();
		
	}
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
//		return  NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	} 

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
	   return new CustomAuthenticationSuccessHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	   return new CustomAccessDeniedHandler();
	}
}
