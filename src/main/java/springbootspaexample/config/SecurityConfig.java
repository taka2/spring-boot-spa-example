package springbootspaexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/login")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
	            // LOGIN
	            .formLogin()
	                .loginProcessingUrl("/login").permitAll()
	                    .usernameParameter("username")
	                    .passwordParameter("password")
	            .and()
	            // LOGOUT
	            .logout()
	                .logoutUrl("/logout")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	            .and()
	            // CSRF
	            .csrf()
	                .disable()
	            ;
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}
