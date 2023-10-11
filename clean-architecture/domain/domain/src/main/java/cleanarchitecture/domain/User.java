package cleanarchitecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(of = {"uid", "version"})
public class User {
    private final UUID uid;
    private final long version;
    private final ZonedDateTime createdDate;
    private final ZonedDateTime lastModifiedDate;
    private final String firstname;
    private final String lastname;
    private final String emailAddress;
}
