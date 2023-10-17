package pipeandfilterarchitecture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = ApplicationConfiguration.class)
@EntityScan(basePackageClasses = ApplicationConfiguration.class)
@EnableJpaRepositories(basePackageClasses = ApplicationConfiguration.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationConfiguration {

}
