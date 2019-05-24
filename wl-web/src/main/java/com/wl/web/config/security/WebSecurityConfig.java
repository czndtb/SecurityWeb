package com.wl.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wl.web.config.jwt.JwtAccessDeniedHandler;
import com.wl.web.config.jwt.JwtAuthenticationEntryPoint;
import com.wl.web.config.jwt.JwtAuthorizationTokenFilter;
import com.wl.web.config.jwt.JwtTokenUtil;
import com.wl.web.config.jwt.service.impl.DatabaseUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	JwtAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private DatabaseUserDetailsService databaseUserDetailsService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.route.authentication.path}")
	private String authenticationPath;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(databaseUserDetailsService).passwordEncoder(passwordEncoderBean());
	}

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AccessDecisionManager webAccessDecisionManager() {
		return new WebAccessDecisionManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.accessDeniedHandler(accessDeniedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/common/**/**").permitAll().antMatchers("/auth/**").permitAll().anyRequest()
				.authenticated().accessDecisionManager(webAccessDecisionManager());

		// Custom JWT based security filter
		JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(),
				jwtTokenUtil, tokenHeader);
		httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().frameOptions().sameOrigin() // required to set for H2 else H2 Console will be blank.
				.cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web.ignoring().antMatchers(HttpMethod.POST, authenticationPath).and().ignoring()
				.antMatchers(HttpMethod.GET, "/", "/static/**/**", "/static/*.html", "/favicon.ico",
						"/static/**/*.html", "/static/**/*.css", "/static/**/*.js")
				.and().ignoring().antMatchers("/exception/**/**");
	}
}
