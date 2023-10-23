package strategypattern;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FileReaderServiceIntegrationTest.TestContext.class)
public class FileReaderServiceIntegrationTest {
    @ContextConfiguration
    @ComponentScan(basePackageClasses = FileReaderService.class)
    static class TestContext { }

    @Autowired private FileReaderService fileReaderService;

    @TestFactory
    Stream<DynamicTest> testReadFile() {
        var mockCsvFile = mock(File.class);
        when(mockCsvFile.getName()).thenReturn("test.csv");

        var mockPdfFile = mock(File.class);
        when(mockPdfFile.getName()).thenReturn("test.pdf");

        var mockUnsupportedFile = mock(File.class);
        when(mockUnsupportedFile.getName()).thenReturn("test.unsupported");

        return Stream.of(
            DynamicTest.dynamicTest("CSV", () -> verifyReadFile(mockCsvFile, true)),
            DynamicTest.dynamicTest("PDF", () -> verifyReadFile(mockPdfFile, true)),
            DynamicTest.dynamicTest("UNSUPPORTED", () -> verifyReadFile(mockUnsupportedFile, false))
        );
    }

    void verifyReadFile(File file, boolean expectIsSupportedFile) {
        // When & Then
        if (expectIsSupportedFile) {
            fileReaderService.readFile(file);
        } else {
            assertThatThrownBy(() -> fileReaderService.readFile(file))
                .isInstanceOf(FileReaderService.UnsupportedFileFormatException.class);
        }
    }
}
