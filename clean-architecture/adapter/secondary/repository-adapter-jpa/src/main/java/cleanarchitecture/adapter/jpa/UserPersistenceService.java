package cleanarchitecture.adapter.jpa;

import cleanarchitecture.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPersistenceService {
    private final UserJpaRepository jpaRepository;
    private final UserJpaMapper jpaMapper;

    @Transactional
    public User persist(User user) {
        return jpaRepository
            .findById(user.getUid())
            .map(existingUser -> jpaMapper.merge(user, existingUser))
            .map(jpaRepository::save)
            .map(jpaMapper::map)
            .orElseGet(() -> jpaMapper.map(jpaRepository.save(jpaMapper.merge(user, new UserEntity()))));
    }
}
