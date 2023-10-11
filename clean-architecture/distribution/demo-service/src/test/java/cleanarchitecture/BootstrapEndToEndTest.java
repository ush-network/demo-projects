package cleanarchitecture;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@SpringBootTest(classes = SpringBootAutoConfiguration.class)
class BootstrapEndToEndTest {
    @Autowired private ApplicationContext applicationContext;
    @Autowired private EntityManager entityManager;

    @Test
    void testDependencyInjection() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    @Transactional
    void testAopTransaction() {
        assertThat(entityManager.isJoinedToTransaction()).isTrue();
    }
}
