package se.lexicon.laserbrain1613.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable("bookId") int bookId) {
        BookDto result = bookService.findById(bookId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        BookDto result = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
        BookDto result = bookService.create(bookDto);
        return ResponseEntity.ok(result);
    }

//    b. Create a general find method that is able to find by title, available, reserved and all.
//    Use @RequestParam to define the search parameters. (tip: Make 4 optional params
//    with “all” marked as default)

}
