package cleanarchitecture.adapter.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "uid", "version" })
class UserEntity implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID uid;

    @Version
    @Column(nullable = false, length = 19)
    private long version;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdDate;

    private ZonedDateTime lastModifiedDate;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String emailAddress;
}
