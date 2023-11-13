package sirin.example.todolistbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
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
            .authorizeHttpRequests(
                request -> request
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/", "/h2-console/**", "/oauth2/**", "/api/**").permitAll()
                    .requestMatchers("/api/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
            )
            .oauth2Login(
                login -> login
                    .userInfoEndpoint(endpoint -> endpoint.userService(authService))
                    .successHandler(oAuthAuthenticationSuccessHandler)
            )
            .csrf(AbstractHttpConfigurer::disable)
            .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
