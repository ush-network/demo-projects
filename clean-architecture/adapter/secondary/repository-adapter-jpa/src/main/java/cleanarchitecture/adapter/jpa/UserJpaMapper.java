package cleanarchitecture.adapter.jpa;

import cleanarchitecture.domain.User;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
interface UserJpaMapper {
    UserEntity merge(User model, @MappingTarget UserEntity entity);
    User map(UserEntity entity);
}
