package strategypattern;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import strategypattern.strategy.FileReaderStrategy;

import java.io.File;
import java.util.Set;

@Service
@RequiredArgsConstructor
class FileReaderService {
    private final Set<FileReaderStrategy> fileReaderStrategies;

    static class UnsupportedFileFormatException extends IllegalArgumentException {
        public UnsupportedFileFormatException(File file) {
            super(String.format(
                "No implementation of strategy '%s' available that can handle file '%s'.",
                FileReaderStrategy.class.getName(), file));
        }
    }

    public void readFile(File file) {
        fileReaderStrategies.stream()
            .filter(strategy -> strategy.supports(file))
            .findFirst()
            .ifPresentOrElse(strategy -> strategy.execute(file), () -> {
                throw new UnsupportedFileFormatException(file);
            });
    }
}
