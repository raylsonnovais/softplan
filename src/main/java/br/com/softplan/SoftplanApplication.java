package br.com.softplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
@EnableCaching
public class SoftplanApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftplanApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return route(
                GET("/"), req -> ServerResponse.temporaryRedirect(URI.create("/swagger-ui/index.html")).build());
    }
}
