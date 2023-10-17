package pipeandfilterarchitecture.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import pipeandfilterarchitecture.AbstractIntegrationTest;
import pipeandfilterarchitecture.CurrencyDefinitionTestFactory;
import pipeandfilterarchitecture.business.CurrencyDefinition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext
class CurrencyDefinitionRepositoryImplIntegrationTest extends AbstractIntegrationTest {
    @MockBean private CurrencyDefinitionJpaRepository mockJpaRepository;
    @Autowired private CurrencyDefinitionRepositoryImpl repository;
    @Autowired private CurrencyDefinitionJpaMapper jpaMapper;

    @Test
    @Transactional(readOnly = true)
    void testExecute() {
        // Given
        var currencyDefinitions = CurrencyDefinitionTestFactory
            .getCurrencyDefinitions()
            .map(model -> CurrencyDefinitionEntity.builder()
                .uid(model.getUid())
                .code(model.getCode())
                .symbol(model.getSymbol())
                .name(model.getName())
                .build())
            .toList();

        // Setup
        when(mockJpaRepository.findAll()).thenReturn(currencyDefinitions.stream());

        // When
        var result = repository.getCurrencyDefinitions().toList();

        // Then
        verify(mockJpaRepository).findAll();
        assertThat(result).containsExactly(currencyDefinitions.stream().map(jpaMapper::map)
            .toArray(CurrencyDefinition[]::new));
    }
}
