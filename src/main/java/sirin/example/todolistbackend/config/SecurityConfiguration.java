package sirin.example.todolistbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import sirin.example.todolistbackend.controller.auth.LoggingFilter;
import sirin.example.todolistbackend.controller.auth.OAuthAuthenticationSuccessHandler;
import sirin.example.todolistbackend.entity.type.Role;
import sirin.example.todolistbackend.service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthService authService;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                request -> request
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/", "/h2-console/**", "/oauth2/**").permitAll()
                    .requestMatchers("/api/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
            )
            .oauth2Login(
                login -> login
                    .userInfoEndpoint(endpoint -> endpoint.userService(authService))
                    .successHandler(oAuthAuthenticationSuccessHandler)
            )
            .addFilterBefore(new LoggingFilter(), SecurityContextHolderFilter.class);

        return http.build();
    }

}
