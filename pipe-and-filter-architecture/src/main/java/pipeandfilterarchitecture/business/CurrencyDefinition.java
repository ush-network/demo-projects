package pipeandfilterarchitecture.business;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CurrencyDefinition {
    private final UUID uid;
    private final String code;
    private final String symbol;
    private final String name;
}
