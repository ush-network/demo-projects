package strategypattern.strategy;

import java.io.File;

public interface FileReaderStrategy {
    boolean supports(File file);
    void execute(File file);
}
