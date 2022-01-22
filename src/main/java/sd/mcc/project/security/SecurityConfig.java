/**
 * 
 */
package sd.mcc.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import sd.mcc.project.service.IUserAuthentication;

/**
 * @author ahmedozy
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.antMatcher("/api/**") .csrf().disable() .authorizeRequests()
	 * .anyRequest().permitAll(); }
	 */
	

	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private IUserAuthentication userAuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(SecurityConstants.SIGN_UP_URL, "/api/case/all", "/api/case/get/**").permitAll()
                .antMatchers("/api/wallet/requestCredit")
                .hasAuthority("USER")
                .antMatchers("/api/wallet/approveCredit","/api/wallet/rejectCredit","/api/case/create","/api/case/agent")
                .hasAuthority("AGENT")
                .antMatchers("/api/donation/**")
                .hasAnyAuthority("USER","AGENT")
                .anyRequest().authenticated()
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager(), userAuthenticationService, passwordEncoder))
                .addFilter(new CustomAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
