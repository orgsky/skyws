package com.inca.skyws.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SkySecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SkySecurityConfig.class);

	private static String[] permitPath = { "/", "/toReg", "/register", "/error", "/login", "/frag/**", "/static/**",
			"/webjars/**","/user/**","/group/**","/ueditor/**","/goods/**" };
	@Autowired
	SkyUserDetailsService detailService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	DataSource dataSource;
	@Autowired
	PersistentTokenRepository tokenRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and().authorizeRequests().antMatchers(permitPath).permitAll()
				.antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
				.failureForwardUrl("/login?error").and().rememberMe().tokenRepository(tokenRepository)
				.tokenValiditySeconds(5000).userDetailsService(detailService).and().csrf().disable();
		log.info("配置拦截路径：" + http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailService).passwordEncoder(encoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		// 配置数据源
		jdbcTokenRepository.setDataSource(dataSource);
//		jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}

}
