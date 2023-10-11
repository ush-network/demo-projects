package cleanarchitecture.adapter.rest;

import cleanarchitecture.adapter.rest.dto.UserCreateRequestDto;
import cleanarchitecture.domain.User;
import cleanarchitecture.domain.UserCreateUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest(classes = WebServiceAdapterRestProviderAutoConfiguration.class)
class UserRestApiControllerIntegrationTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private UserCreateUseCase mockUserCreateUseCase;
    @Captor private ArgumentCaptor<UserCreateUseCase.Request> createRequestArgumentCaptor;

    @Test
    void testCreate() throws Exception {
        // Given
        var dto = new UserCreateRequestDto();
        dto.emailAddress("bob.marley@example.org");
        dto.setFirstname("Bob");
        dto.setLastname("Marley");

        // Setup
        when(mockUserCreateUseCase.execute(any(UserCreateUseCase.Request.class))).thenReturn(User.builder()
            .uid(UUID.randomUUID())
            .version(1)
            .createdDate(ZonedDateTime.now())
            .lastModifiedDate(ZonedDateTime.now())
            .emailAddress(dto.getEmailAddress())
            .firstname(dto.getFirstname())
            .lastname(dto.getLastname())
            .build());

        // When & Then
        mockMvc.perform(post("/api/v1/user")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(dto)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.uid").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.version").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.createdDate").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastModifiedDate").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.emailAddress").value(dto.getEmailAddress()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value(dto.getFirstname()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(dto.getLastname()));

        verify(mockUserCreateUseCase).execute(createRequestArgumentCaptor.capture());

        var request = createRequestArgumentCaptor.getValue();
        assertThat(request.getUid()).isNull();
        assertThat(request.getEmailAddress()).isEqualTo(dto.getEmailAddress());
        assertThat(request.getFirstname()).isEqualTo(dto.getFirstname());
        assertThat(request.getLastname()).isEqualTo(dto.getLastname());
    }
}
