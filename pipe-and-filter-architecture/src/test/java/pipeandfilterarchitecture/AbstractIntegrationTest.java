package pipeandfilterarchitecture;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = ApplicationConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class AbstractIntegrationTest {
    @Autowired @Getter
    protected MockMvc mockMvc;
}
