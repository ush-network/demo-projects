package cleanarchitecture.domain;

import cleanarchitecture.adapter.jpa.UserPersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRepositoryImplTest {
    @Test
    void testPersist() {
        // Given
        var user = Mockito.mock(User.class);

        // Setup
        var mockPersistenceService = Mockito.mock(UserPersistenceService.class);
        when(mockPersistenceService.persist(user)).thenReturn(user);
        var repository = new UserRepositoryImpl(mockPersistenceService);

        // When
        var persistedUser = repository.persist(user);

        // Then
        verify(mockPersistenceService).persist(user);
        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser).isSameAs(user);
    }
}
