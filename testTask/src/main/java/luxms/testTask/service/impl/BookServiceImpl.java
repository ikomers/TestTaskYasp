package luxms.testTask.service.impl;

import lombok.AllArgsConstructor;
import luxms.testTask.model.Book;
import luxms.testTask.repositories.BookRepository;
import luxms.testTask.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getTop10Books(Integer year, String column, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;

        if (sort.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }

        Sort sorting = Sort.by(direction, column);

        if (year != null) {
            return bookRepository.findTop10ByPublicationDateYearOrderByPublicationDateAsc(year, sorting);
        } else {
            return bookRepository.findTop10(sorting);
        }
    }
}
