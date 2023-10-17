package pipeandfilterarchitecture.business;

import java.util.stream.Stream;

public interface GetCurrencyDefinitionStreamUseCase {
    Stream<CurrencyDefinition> execute();
}
