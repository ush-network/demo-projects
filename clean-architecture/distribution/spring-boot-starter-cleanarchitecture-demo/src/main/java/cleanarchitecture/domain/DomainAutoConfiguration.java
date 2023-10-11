package cleanarchitecture.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainAutoConfiguration {
    @Bean
    UserCreateUseCase userCreateUseCase(UserRepository repository) {
        return new UserCreateUseCaseImpl(repository);
    }
}
