package pipeandfilterarchitecture.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CurrencyDefinitionEntity implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID uid;

    @Column(nullable = false, updatable = false, unique = true, length = 3)
    private String code;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(nullable = false)
    private String name;
}
