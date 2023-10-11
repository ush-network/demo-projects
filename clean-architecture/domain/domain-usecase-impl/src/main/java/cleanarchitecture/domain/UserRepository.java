package cleanarchitecture.domain;

interface UserRepository {
    User persist(User user);
}
