package luxms.testTask.service.impl;

import com.opencsv.CSVReader;

import luxms.testTask.model.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CSVBookLoaderServiceImpl {

    private static final Log log = LogFactory.getLog(CSVBookLoaderServiceImpl.class);

    private List<Book> books;
    public void loadBooks(String filePath) {
        books = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
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
            log.warn("Error when reading data from CSV file");
            log.error(e.getMessage());
        }
    }

    private Long parseLong(String value) {
        try {
            return value.trim().isEmpty() ? null : Long.parseLong(value);
        } catch (NumberFormatException e) {
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

    private LocalDateTime parseDate(String value) {
        try {
            return value.trim().isEmpty() ? null : new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
                    .parse(value).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (ParseException e) {
            try {
                return value.trim().isEmpty() ? null : new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
                        .parse(value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException ex) {
             //todo what if unset? it's return standart 1999-01-01 check it or add some if?
                return null;
            }
        }
    }

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
