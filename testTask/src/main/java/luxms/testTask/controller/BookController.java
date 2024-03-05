package luxms.testTask.controller;

import lombok.RequiredArgsConstructor;
import luxms.testTask.model.Book;
import luxms.testTask.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/top10")
    public List<Book> getTop10Books(
            @RequestParam(required = false) Integer year,
            @RequestParam String column,
            @RequestParam String sort
    ) {
        return bookService.getTop10Books(year, column, sort);
    }
}
