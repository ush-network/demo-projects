package pipeandfilterarchitecture.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class GetCurrencyDefinitionStreamUseCaseImpl implements GetCurrencyDefinitionStreamUseCase {
    private final CurrencyDefinitionRepository repository;

    @Override
    public Stream<CurrencyDefinition> execute() {
        return repository.getCurrencyDefinitions();
    }
}
