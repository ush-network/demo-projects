package cleanarchitecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

public interface UserCreateUseCase {
    @Data
    @Builder
    @ToString
    class Request {
        private final UUID uid;
        private final String emailAddress;
        private final String firstname;
        private final String lastname;
    }

    User execute(Request request);
}
