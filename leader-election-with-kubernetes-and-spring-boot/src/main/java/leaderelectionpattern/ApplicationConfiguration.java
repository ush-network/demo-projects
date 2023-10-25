package leaderelectionpattern;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Configuration
@EnableScheduling
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackageClasses = ApplicationConfiguration.class)
class ApplicationConfiguration {
    @Bean
    ApiClient k8sApiClient(LeaderElectionProperties properties) throws IOException {
        var client = Config.defaultClient();
        client.setDebugging(properties.isDebugMode());

        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);

        return client;
    }
}
