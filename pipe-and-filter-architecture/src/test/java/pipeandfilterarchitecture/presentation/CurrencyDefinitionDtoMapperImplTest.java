package pipeandfilterarchitecture.presentation;

import org.junit.jupiter.api.Test;
import pipeandfilterarchitecture.business.CurrencyDefinition;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyDefinitionDtoMapperImplTest {
    private static final CurrencyDefinitionDtoMapperImpl mapper = new CurrencyDefinitionDtoMapperImpl();

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
        var dto = CurrencyDefinitionDto.builder()
            .uid(model.getUid())
            .code(model.getCode())
            .name(model.getName())
            .symbol(model.getSymbol())
            .build();

        // When
        var result = mapper.map(model);

        // Then
        assertThat(result).usingRecursiveComparison().isEqualTo(dto);
    }
}
