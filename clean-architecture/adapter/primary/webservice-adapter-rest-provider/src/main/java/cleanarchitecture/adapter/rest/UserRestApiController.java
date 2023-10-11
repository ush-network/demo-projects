package cleanarchitecture.adapter.rest;

import cleanarchitecture.adapter.rest.dto.UserCreateRequestDto;
import cleanarchitecture.adapter.rest.dto.UserDto;
import cleanarchitecture.domain.UserCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserRestApiController implements UserApi {
    private final UserCreateUseCase userCreateUseCase;
    private final UserDtoMapper userDtoMapper;

    @Override
    public ResponseEntity<UserDto> create(UserCreateRequestDto dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userDtoMapper.map(userCreateUseCase.execute(userDtoMapper.map(dto))));
    }
}
