package pipeandfilterarchitecture;

import pipeandfilterarchitecture.business.CurrencyDefinition;

import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

public class CurrencyDefinitionTestFactory {
    private CurrencyDefinitionTestFactory() {

    }

    public static Stream<CurrencyDefinition> getCurrencyDefinitions() {
        return Currency.getAvailableCurrencies().stream()
            .sorted(Comparator.comparing(Currency::getCurrencyCode))
            .map(currency -> CurrencyDefinition.builder()
                .uid(UUID.randomUUID())
                .code(currency.getCurrencyCode())
                .symbol(currency.getSymbol())
                .name(currency.getDisplayName(Locale.US))
                .build());
    }
}
