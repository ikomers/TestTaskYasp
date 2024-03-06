package luxms.testTask.controller;

import lombok.RequiredArgsConstructor;
import luxms.testTask.model.Book;
import luxms.testTask.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
    public ResponseEntity<List<Book>> getTop10Books(
            @RequestParam(required = false) Integer year,
            @RequestParam String column,
            @RequestParam String sort
    ) {
        try {
            List<Book> top10Books = bookService.getTop10Books(year, column, sort);
            return ResponseEntity.ok().body(top10Books);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
