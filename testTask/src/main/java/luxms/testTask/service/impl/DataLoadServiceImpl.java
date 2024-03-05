package luxms.testTask.service.impl;

import luxms.testTask.service.DataLoadService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataLoadServiceImpl implements CommandLineRunner, DataLoadService {

    private static final Log log = LogFactory.getLog(CSVBookLoaderServiceImpl.class);
    private final String filePath;
    private final CSVBookLoaderServiceImpl csvBookLoaderService;

    public DataLoadServiceImpl(CSVBookLoaderServiceImpl csvBookLoaderService, @Value("${dataset.file.path}") String filePath) {

        this.filePath = filePath;
        this.csvBookLoaderService = csvBookLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            csvBookLoaderService.loadBooks(filePath);
        } catch (Exception e) {
            log.error("Error while loading CSV file");
            log.error(e.getMessage());
        }
    }
}
