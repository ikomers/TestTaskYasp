package luxms.testTask.service.impl;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luxms.testTask.model.Book;
import luxms.testTask.service.CSVBookLoaderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Service
@Scope
@Slf4j
public class CSVBookLoaderServiceImpl implements CSVBookLoaderService {
    private List<Book> books;

    public void loadBooks(String filePath) {
        books = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(',').withQuoteChar('"').build()).build()) {
            String[] line;
            // skip the title
            reader.readNext();

            while ((line = reader.readNext()) != null) {

                Book book = Book.builder()
                        .id(parseLong(line[0]))
                        .isbn(parseLong(line[1]))
                        .title(parseString(line[2]))
                        .seriesTitle(parseString(line[3]))
                        .seriesReleaseNumber(parseString(line[4]))
                        .authors(parseString(line[5]))
                        .publisher(parseString(line[6]))
                        .language(parseString(line[7]))
                        .description(parseString(line[8]))
                        .numPages(parseInt(line[9]))
                        .format(parseString(line[10]))
                        .genres(parseGenres(line[11]))
                        .publicationDate(parseDate(line[12]))
                        .ratingScore(parseDouble(line[13]))
                        .numRatings(parseDouble(line[14]))
                        .numReviews(parseDouble(line[15]))
                        .currentReaders(parseDouble(line[16]))
                        .wantToRead(parseDouble(line[17]))
                        .price(parseBigDecimal(line[18]))
                        .url(parseUrl(line[19]))
                        .build();

                books.add(book);
            }
        } catch (Exception e) {
            log.warn("Error when reading data from CSV file " + "\n" + e.getMessage());
        }
    }

    private Long parseLong(String value) {
        try {
            return value.trim().isEmpty() ? null : Long.parseLong(value);
        } catch (NumberFormatException e) {
            //log to file bla bla
            return null;
        }
    }

    private Integer parseInt(String value) {
        try {
            return value.trim().isEmpty() ? null : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            return value.trim().isEmpty() ? null : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private LocalDate parseDate(String value) {
        try {
            return value.trim().isEmpty() ? null :
                    LocalDate.parse(value, DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //
    private String parseString(String value) {
        return value.trim().isEmpty() ? null : value;
    }

    private List<String> parseGenres(String value) {
        String[] genresArray = value.trim().isEmpty() ? new String[0] : value.split(",");
        List<String> genres = new ArrayList<>();
        for (String genre : genresArray) {
            genres.add(genre.trim());
        }
        return genres.isEmpty() ? null : genres;
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return value.trim().isEmpty() ? null : new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private URL parseUrl(String value) {
        try {
            return value.trim().isEmpty() ? null : new URL(value);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
