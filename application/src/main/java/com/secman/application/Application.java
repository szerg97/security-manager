package com.secman.application;

import com.secman.service.SeedService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SecurityScheme(
        type = SecuritySchemeType.OAUTH2,
        name = "oauth2",
        description = "KeyCloak Gsec",
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://localhost:6080/auth/realms/iam/protocol/openid-connect/auth?" +
                        "client_id=account&" +
                        "redirect_uri=http://localhost:8080/security-manager/swagger-ui/oauth2-redirect.html&" +
                        "response_type=code&" +
                        "scope=openid")
        )
)

@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "apikey",
        paramName = "Authorization",
        description = "KeyCloak Gsec",
        in = SecuritySchemeIn.HEADER
)


@SecurityScheme(
        type = SecuritySchemeType.OPENIDCONNECT,
        name = "openid",
        description = "KeyCloak Gsec",
        openIdConnectUrl = "http://localhost:6080/auth/realms/iam/.well-known/openid-configuration"
)

@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080/security-manager", description = "local dev")},

        info = @Info(
                title = "Gsec API",
                version = "v1",
                description = "Gsec API for Graphical User Interface ."
        ))

@Configuration
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = {"com.secman.*"})
@EntityScan(basePackages = {"com.secman.*"})
@EnableJpaRepositories(basePackages = {"com.secman.*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**");
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            SeedService seeder
    ){
        return args -> {
            seeder.seedCountries();
            seeder.seedCities();
            seeder.seedAddresses();
            seeder.seedCustomers();
            seeder.seedOpeningHours();
            seeder.seedIssuers();
            seeder.seedEmployees();
            seeder.seedMessages();
            seeder.seedFeedbacks();
            seeder.seedCategories();
            seeder.seedPortfolios();
            seeder.seedTransactions();
        };
    }
}
