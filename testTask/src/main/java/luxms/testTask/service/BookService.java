package luxms.testTask.service;

import luxms.testTask.model.Book;

import java.util.List;


public interface BookService {
    List<Book> getTop10Books(Integer year, String column, String sort);
}
