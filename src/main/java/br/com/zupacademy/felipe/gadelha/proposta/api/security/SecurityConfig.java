package br.com.zupacademy.felipe.gadelha.proposta.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a -> a
        	.antMatchers("/swagger-ui/**", "/v3/api-docs/swagger-config/**").permitAll()	
        	.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
        	.antMatchers(HttpMethod.GET, "/actuator/prometheus").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/proposals/**").hasAuthority("SCOPE_proposal-scope:read")
            .antMatchers(HttpMethod.POST, "/v1/proposals/**").hasAuthority("SCOPE_proposal-scope:write")
            .antMatchers(HttpMethod.POST, "/v1/cards/**").hasAuthority("SCOPE_proposal-scope:write")
            .anyRequest().authenticated())
        .sessionManagement(
        		s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf().disable()
//        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring()
    	.antMatchers(
				"/**.html", 
				"/swagger-ui/**", 
				"/v3/api-docs/", 
				"/webjars/**", 
				"/configuration/**", 
				"/swagger-resources/**",
				"/swagger-ui/**",
				"/v3/api-docs/swagger-config/**"
				);
    }
}
