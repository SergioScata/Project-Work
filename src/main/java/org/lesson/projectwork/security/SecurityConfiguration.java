package org.lesson.projectwork.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public DatabaseUserDetailsService userDetailsService(){return new DatabaseUserDetailsService();}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http.authorizeHttpRequests()
                .requestMatchers("/amministrazione/edit/**", "/amministrazione/delete/**")
                .hasAuthority("ADMIN")
                .requestMatchers("/shop", "/shop/show/**", "/shop/create/**").hasAnyAuthority("ADMIN", "USER")
               .requestMatchers("/amministrazione").hasAnyAuthority("ADMIN")
                .requestMatchers("/amministrazione/show/**").hasAuthority("Admin")
                .requestMatchers("/","/**").permitAll()
               .requestMatchers(HttpMethod.POST, "/shop/**").hasAuthority("ADMIN")
               .requestMatchers(HttpMethod.POST, "/amministrazione/**").hasAuthority("ADMIN")
               .requestMatchers(HttpMethod.POST, "/assortimenti/**").hasAuthority("ADMIN")
                .and().formLogin()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling()
                .and().csrf().disable();

        return http.build();

    }
}
