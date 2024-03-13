package luxms.testTask.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatasetLoadService implements CommandLineRunner {
    private final CSVBookLoaderServiceImpl csvBookLoaderService;
    private final ResourceLoader resourceLoader;
    private final String filePath;

    public DatasetLoadService(
            CSVBookLoaderServiceImpl csvBookLoaderService,
            ResourceLoader resourceLoader,
            @Value("${dataset.file.path}") String filePath) {
        this.resourceLoader = resourceLoader;
        this.filePath = filePath;
        this.csvBookLoaderService = csvBookLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Resource resource = resourceLoader.getResource(filePath);
            csvBookLoaderService.loadBooks(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            log.error("Error while loading CSV file " + "\n" + e.getMessage());
        }
    }
}
