package cleanarchitecture.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class UserCreateUseCaseImpl implements UserCreateUseCase {
    private final UserRepository repository;
    @Override
    public User execute(Request request) {
        log.info("process request to register new user => {}", request);
        return repository.persist(User.builder()
            .uid(request.getUid() != null ? request.getUid() : UUID.randomUUID())
            .version(0)
            .createdDate(ZonedDateTime.now(ZoneOffset.UTC))
            .emailAddress(request.getEmailAddress())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .build());
    }
}
