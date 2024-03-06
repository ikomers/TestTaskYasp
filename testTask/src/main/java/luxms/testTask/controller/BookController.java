package luxms.testTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luxms.testTask.model.Book;
import luxms.testTask.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/top10")
    public ResponseEntity<?> getTop10Books(
            @RequestParam(required = false) Integer year,
            @RequestParam String column,
            @RequestParam String sort
    ) {
        try {
            List<Book> top10Books = bookService.getTop10Books(year, column, sort);
            log.info("Request processed successfully.");
            return ResponseEntity.ok().body(top10Books);
        } catch (IllegalArgumentException e) {
            log.error("Invalid request parameters.", e);
            return ResponseEntity.badRequest().body("Invalid request parameters. \n" +
                    "Please check that the request parameters are correct" + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while processing the request.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}
