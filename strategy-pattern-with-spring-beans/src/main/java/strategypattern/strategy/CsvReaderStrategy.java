package strategypattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
class CsvReaderStrategy implements FileReaderStrategy {
    @Override
    public boolean supports(File file) {
        return file != null && file.getName().toLowerCase().endsWith(".csv");
    }

    @Override
    public void execute(File file) {
        log.info("Reading CSV File '{}'", file);
    }
}
