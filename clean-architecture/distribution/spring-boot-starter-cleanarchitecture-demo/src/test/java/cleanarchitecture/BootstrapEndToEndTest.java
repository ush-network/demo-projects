package cleanarchitecture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@SpringBootTest(classes = SpringBootAutoConfiguration.class)
class BootstrapEndToEndTest {
    @Autowired private ApplicationContext applicationContext;

    @Test
    void testDependencyInjection() {
        assertThat(applicationContext).isNotNull();
    }
}
