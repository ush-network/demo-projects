package pipeandfilterarchitecture.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pipeandfilterarchitecture.AbstractIntegrationTest;
import pipeandfilterarchitecture.CurrencyDefinitionTestFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetCurrencyDefinitionStreamUseCaseImplIntegrationTest extends AbstractIntegrationTest {
    @MockBean private CurrencyDefinitionRepository mockRepository;
    @Autowired private GetCurrencyDefinitionStreamUseCase useCase;

    @Test
    void testExecute() {
        // Given
        var currencyDefinitions = CurrencyDefinitionTestFactory.getCurrencyDefinitions().toList();

        // Setup
        when(mockRepository.getCurrencyDefinitions()).thenReturn(currencyDefinitions.stream());

        // When
        var result = useCase.execute().toList();

        // Then
        verify(mockRepository).getCurrencyDefinitions();
        assertThat(result).containsExactly(currencyDefinitions.toArray(CurrencyDefinition[]::new));
    }
}
