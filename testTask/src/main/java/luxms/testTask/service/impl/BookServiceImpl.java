package luxms.testTask.service.impl;

import lombok.RequiredArgsConstructor;
import luxms.testTask.model.Book;
import luxms.testTask.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Override
    public List<Book> getTop10Books(Integer year, String column, String sort) {
        //todo
        return null;
    }
}
