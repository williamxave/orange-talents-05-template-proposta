package br.com.zupacademy.projetoproposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
public class ConfiguracoesDeSeguranca extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET,"/actuator/prometheus").permitAll()
                .antMatchers(HttpMethod.GET,"/proposta/*").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.POST,"/proposta").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.POST,"/cartao/**").hasAuthority("SCOPE_user")
                        .antMatchers(HttpMethod.POST,"/viagem").hasAuthority("SCOPE_user")
                .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}

