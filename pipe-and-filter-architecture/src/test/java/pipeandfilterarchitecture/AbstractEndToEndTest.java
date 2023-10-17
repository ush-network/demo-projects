package pipeandfilterarchitecture;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApplicationConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractEndToEndTest {
}
