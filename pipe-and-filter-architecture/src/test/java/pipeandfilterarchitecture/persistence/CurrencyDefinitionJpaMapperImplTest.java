package pipeandfilterarchitecture.persistence;

import org.junit.jupiter.api.Test;
import pipeandfilterarchitecture.business.CurrencyDefinition;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyDefinitionJpaMapperImplTest {
    private static final CurrencyDefinitionJpaMapper mapper = new CurrencyDefinitionJpaMapperImpl();

    @Test
    void testMap() {
        // Given
        var currency = Currency.getInstance("USD");
        var model = CurrencyDefinition.builder()
            .uid(UUID.randomUUID())
            .code(currency.getCurrencyCode())
            .name(currency.getDisplayName(Locale.US))
            .symbol(currency.getSymbol())
            .build();
        var entity = CurrencyDefinitionEntity.builder()
            .uid(model.getUid())
            .code(model.getCode())
            .name(model.getName())
            .symbol(model.getSymbol())
            .build();

        // When
        var result = mapper.map(entity);

        // Then
        assertThat(result).usingRecursiveComparison().isEqualTo(model);
    }
}
