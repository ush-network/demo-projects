package cleanarchitecture.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserCreateUseCaseImplTest {
    @Test
    void testExecute() {
        // Given
        var request = UserCreateUseCase.Request.builder()
            .emailAddress("demo.user@example.org")
            .firstname("Bob")
            .lastname("Marley")
            .build();

        // Setup
        var mockRepository = mock(UserRepository.class);
        var usecase = new UserCreateUseCaseImpl(mockRepository);
        when(mockRepository.persist(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        var user = usecase.execute(request);

        // Then
        assertThat(user).isNotNull();
        assertThat(user).extracting(User::getUid).isNotNull();
        assertThat(user).extracting(User::getEmailAddress).isEqualTo(request.getEmailAddress());
        assertThat(user).extracting(User::getFirstname).isEqualTo(request.getFirstname());
        assertThat(user).extracting(User::getLastname).isEqualTo(request.getLastname());

        verify(mockRepository).persist(user);
    }
}
