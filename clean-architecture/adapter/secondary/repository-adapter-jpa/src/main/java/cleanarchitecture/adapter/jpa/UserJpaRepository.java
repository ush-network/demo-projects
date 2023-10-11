package cleanarchitecture.adapter.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserJpaRepository extends CrudRepository<UserEntity, UUID> {
}
