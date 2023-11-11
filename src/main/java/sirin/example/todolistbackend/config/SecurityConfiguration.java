package sirin.example.todolistbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import sirin.example.todolistbackend.entity.type.Role;
import sirin.example.todolistbackend.service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthService authService;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(
                request -> request
                    .requestMatchers("/api/**").hasRole(Role.USER.name())
            )
            .oauth2Login(
                login -> login
                    .userInfoEndpoint(endpoint -> endpoint.userService(authService))
            )
            .csrf(AbstractHttpConfigurer::disable)
            .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

}
