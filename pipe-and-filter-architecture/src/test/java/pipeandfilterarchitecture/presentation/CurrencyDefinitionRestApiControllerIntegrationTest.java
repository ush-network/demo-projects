package pipeandfilterarchitecture.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import pipeandfilterarchitecture.AbstractIntegrationTest;
import pipeandfilterarchitecture.CurrencyDefinitionTestFactory;
import pipeandfilterarchitecture.business.GetCurrencyDefinitionStreamUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext
class CurrencyDefinitionRestApiControllerIntegrationTest extends AbstractIntegrationTest {
    @MockBean private GetCurrencyDefinitionStreamUseCase mockGetCurrencyDefinitionStreamUseCase;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void testGetCurrencyDefinitions() throws Exception {
        // Given
        var currencyDefinitions = CurrencyDefinitionTestFactory.getCurrencyDefinitions().toList();

        // Setup
        when(mockGetCurrencyDefinitionStreamUseCase.execute()).thenReturn(currencyDefinitions.stream());

        // When & Then
        getMockMvc()
            .perform(get("/api/currency-definition"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(currencyDefinitions)));

        assertThat(currencyDefinitions).isNotEmpty();
    }
}
