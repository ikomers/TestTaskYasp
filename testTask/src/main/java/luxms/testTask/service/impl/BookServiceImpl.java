package luxms.testTask.service.impl;

import lombok.RequiredArgsConstructor;
import luxms.testTask.model.Book;
import luxms.testTask.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final CSVBookLoaderServiceImpl csvBookLoaderService;

    @Override
    public List<Book> getTop10Books(Integer year, String column, String sort) {
        List<Book> books = csvBookLoaderService.getBooks(); // get loaded books

        // stream for filter and sorting
        Stream<Book> bookStream = books.stream();

        if (year != null) {
            bookStream = bookStream.filter(book -> book.getPublicationDate() != null && book.getPublicationDate().getYear() == year);
        }

        // Adding a filter to exclude books with null values for the specified column
        bookStream = bookStream.filter(book -> {
            switch (column) {
                case "book": return book.getTitle() != null;
                case "author": return book.getAuthors() != null;
                case "numPages": return book.getNumPages() != null;
                case "publicationDate": return book.getPublicationDate() != null;
                case "rating": return book.getRatingScore() != null;
                case "numberOfVoters": return book.getNumRatings() != null;
                default: throw new IllegalArgumentException("Invalid column name");
            }
        });

        Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Comparator<Book> comparator = switch (column) {
            case "book" -> Comparator.comparing(Book::getTitle);
            case "author" -> Comparator.comparing(Book::getAuthors);
            case "numPages" -> Comparator.comparing(Book::getNumPages);
            case "publicationDate" -> Comparator.comparing(Book::getPublicationDate);
            case "rating" -> Comparator.comparing(Book::getRatingScore);
            case "numberOfVoters" -> Comparator.comparing(Book::getNumRatings);
            default -> throw new IllegalArgumentException("Invalid column name");
        };

        List<Book> result = bookStream.sorted(comparator.thenComparing(Book::getId))
                .limit(10)
                .collect(Collectors.toList());

        if (direction == Sort.Direction.DESC) {
            Collections.reverse(result);
        }
        return result;
    }
}
