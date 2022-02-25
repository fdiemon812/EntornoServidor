package com.example.demo.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.repository.UserRepo;

//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserRepo userRepo;
    @Autowired private JWTFilter filter;
    @Autowired private MyUserDetailsService uds;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").hasAnyRole("ADMINISTRADOR", "TUTOR")
                .antMatchers("/home/token").permitAll()
                .antMatchers("/alumno").hasAnyRole("ADMINISTRADOR", "PROFESOR")
                .antMatchers("/alumno/{idAlumno}").hasRole("ADMINISTRADOR")
//                .antMatchers("/aula").permitAll()
                .antMatchers("/aula/{id}").hasRole("ADMINISTRADOR")
                .antMatchers("/aulas").hasRole("ADMINISTRADOR")
                .antMatchers("/centro/{id}").hasRole("ADMINISTRADOR")
                .antMatchers("/centros").hasRole("ADMINISTRADOR")
                .antMatchers("/centro/{id}/aulas").hasRole("ADMINISTRADOR")
                .antMatchers("/centro/{id}/aula/{idAula}").hasRole("ADMINISTRADOR")
                .antMatchers("/centro/{id}/aula").hasRole("ADMINISTRADOR")
                .antMatchers("/centro/{id}/alumno").hasRole("ADMINISTRADOR")

                
//                .antMatchers("/user/**").hasRole("USER")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}