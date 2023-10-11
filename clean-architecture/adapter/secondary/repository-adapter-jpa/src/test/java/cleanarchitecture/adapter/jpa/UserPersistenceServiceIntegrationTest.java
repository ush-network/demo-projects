package cleanarchitecture.adapter.jpa;

import cleanarchitecture.RepositoryAdapterJpaAutoConfiguration;
import cleanarchitecture.domain.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@SpringBootTest(classes = RepositoryAdapterJpaAutoConfiguration.class)
class UserPersistenceServiceIntegrationTest {
    @Autowired private UserPersistenceService service;
    @Autowired private EntityManager entityManager;

    @Test
    void testPersist() {
        // Given
        var user = User.builder()
            .uid(UUID.randomUUID())
            .version(Long.MAX_VALUE)
            .createdDate(ZonedDateTime.now(ZoneOffset.UTC))
            .lastModifiedDate(ZonedDateTime.now(ZoneOffset.UTC))
            .emailAddress("bob.marley@example.org")
            .firstname("Bob")
            .lastname("Marley")
            .build();

        // When
        var persistedUser = service.persist(user);

        // Then
        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser).usingRecursiveComparison().isEqualTo(user);
        assertThat(entityManager.find(UserEntity.class, user.getUid())).isNotNull();
    }
}
