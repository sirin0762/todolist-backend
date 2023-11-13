package sirin.example.todolistbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableJpaAuditing
public class TodoListBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListBackendApplication.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener(){    return new RequestContextListener();}

}
