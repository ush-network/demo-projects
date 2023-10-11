package cleanarchitecture.domain;

import cleanarchitecture.adapter.jpa.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRepositoryImpl implements UserRepository {
    private final UserPersistenceService persistenceService;

    @Override
    public User persist(User user) {
        return persistenceService.persist(user);
    }
}
