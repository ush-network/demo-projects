package cleanarchitecture;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan(basePackageClasses = SpringBootAutoConfiguration.class)
public class SpringBootAutoConfiguration {
}
