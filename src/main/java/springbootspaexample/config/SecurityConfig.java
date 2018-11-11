package springbootspaexample.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
	                .loginPage("/")
	                .successForwardUrl("/home")
	            .and()
	            // LOGOUT
	            .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	            .and()
	            // CSRF
	            .csrf()
	            	.ignoringAntMatchers("/login")
	            ;
	}
}
