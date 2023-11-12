package sirin.example.todolistbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 활성화
            .allowedOrigins("http://localhost:5173") // 허용할 원본 (예: 클라이언트 앱의 주소)
            .allowedMethods("*"); // 허용할 HTTP 메서드
    }
}
