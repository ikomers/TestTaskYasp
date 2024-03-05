package luxms.testTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private Long isbn;
    private String title;
    private String seriesTitle;
    private String seriesReleaseNumber;
    private String authors;
    private String publisher;
    private String language;
    private String description;
    private Integer numPages;
    private String format;
    private List<String> genres;
    private LocalDateTime publicationDate;
    private Double ratingScore;
    private Double numRatings;
    private Double numReviews;
    private Double currentReaders;
    private Double wantToRead;
    private BigDecimal price;
    private URL url;
}
