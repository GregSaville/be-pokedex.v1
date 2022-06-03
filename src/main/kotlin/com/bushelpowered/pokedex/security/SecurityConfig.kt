package com.bushelpowered.pokedex.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.savedrequest.NullRequestCache


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    val authenticationEntryPoint = RESTAuthenticationEntryPoint()

    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .requestCache().requestCache(NullRequestCache()).and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .csrf().disable()
            .cors()

        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/pokemon").permitAll()
            .antMatchers(HttpMethod.GET,"/api/trainers/{id}/captured").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/trainers/{id}").permitAll()
            .antMatchers(HttpMethod.GET, "/api/trainers").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/trainers").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/trainers/**").hasAnyRole("ADMIN")

    }
}