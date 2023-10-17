package pipeandfilterarchitecture.business;

import java.util.stream.Stream;

public interface CurrencyDefinitionRepository {
    Stream<CurrencyDefinition> getCurrencyDefinitions();
}
