package luxms.testTask.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatasetLoadService implements CommandLineRunner {
    private final CSVBookLoaderServiceImpl csvBookLoaderService;
    private final String filePath;

    public DatasetLoadService(CSVBookLoaderServiceImpl csvBookLoaderService, @Value("${dataset.file.path}") String filePath) {

        this.filePath = filePath;
        this.csvBookLoaderService = csvBookLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            csvBookLoaderService.loadBooks(filePath);
        } catch (Exception e) {
            log.error("Error while loading CSV file " + "\n" + e.getMessage());
        }
    }
}
