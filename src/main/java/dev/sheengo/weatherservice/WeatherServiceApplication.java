package dev.sheengo.weatherservice;

import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
public class WeatherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorAware(SessionUser sessionUser) {
        return () -> Optional.of(sessionUser.id());
    }

//    @Bean
//    ApplicationRunner runner(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            AuthUser user = AuthUser.childBuilder()
//                    .id(1L)
//                    .createdAt(LocalDateTime.now())
//                    .deleted(false)
//                    .username("user")
//                    .email("user@gmail.com")
//                    .role(AuthUser.ROLES.USER)
//                    .state(AuthUser.STATE.ACTIVE)
//                    .password("1234")
//                    .build();
//            System.out.println(user);
//            authUserRepository.save(user);
//
//            AuthUser admin = AuthUser.childBuilder()
//                    .id(2L)
//                    .createdAt(LocalDateTime.now())
//                    .deleted(false)
//                    .username("admin")
//                    .email("admin@gmail.com")
//                    .role(AuthUser.ROLES.ADMIN)
//                    .state(AuthUser.STATE.ACTIVE)
//                    .password("1234")
//                    .build();
//            authUserRepository.save(admin);
//        };
//    }

}
