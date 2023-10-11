package cleanarchitecture.adapter.rest;

import cleanarchitecture.adapter.rest.dto.UserCreateRequestDto;
import cleanarchitecture.adapter.rest.dto.UserDto;
import cleanarchitecture.domain.User;
import cleanarchitecture.domain.UserCreateUseCase;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
interface UserDtoMapper {
    UserDto map(User model);
    @Mapping(target = "uid", ignore = true)
    UserCreateUseCase.Request map(UserCreateRequestDto dto);
}
