package cleanarchitecture.adapter.jpa;

import cleanarchitecture.domain.User;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserJpaMapperTest {
    private static final User MODEL = User.builder()
        .uid(UUID.randomUUID())
        .version(Long.MAX_VALUE)
        .createdDate(ZonedDateTime.now(ZoneOffset.UTC))
        .lastModifiedDate(ZonedDateTime.now(ZoneOffset.UTC))
        .emailAddress("bob.marley@example.org")
        .firstname("Bob")
        .lastname("Marley")
        .build();
    private static final UserEntity ENTITY = UserEntity.builder()
        .uid(MODEL.getUid())
        .version(MODEL.getVersion())
        .createdDate(MODEL.getCreatedDate())
        .lastModifiedDate(MODEL.getLastModifiedDate())
        .emailAddress(MODEL.getEmailAddress())
        .firstname(MODEL.getFirstname())
        .lastname(MODEL.getLastname())
        .build();

    private UserJpaMapper mapper = new UserJpaMapperImpl();

    @Test
    void testMerge() {
        // Given
        var entity = new UserEntity();

        // When
        var mergedEntity = mapper.merge(MODEL, entity);

        // Then
        assertThat(mergedEntity).isNotNull();
        assertThat(mergedEntity).usingRecursiveComparison().isEqualTo(ENTITY);
    }

    @Test
    void testMap() {
        // When
        var model = mapper.map(ENTITY);

        // Then
        assertThat(model).isNotNull();
        assertThat(model).usingRecursiveComparison().isEqualTo(MODEL);
    }
}
