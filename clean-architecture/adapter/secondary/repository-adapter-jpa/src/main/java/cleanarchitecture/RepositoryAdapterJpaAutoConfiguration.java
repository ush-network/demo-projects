package cleanarchitecture;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = RepositoryAdapterJpaAutoConfiguration.class)
@EntityScan(basePackageClasses = RepositoryAdapterJpaAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = RepositoryAdapterJpaAutoConfiguration.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class RepositoryAdapterJpaAutoConfiguration {
}
