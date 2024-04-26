package com.pblgllgs.backend.book;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import com.pblgllgs.backend.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> createBook(
            @Valid @RequestBody BookRequest bookRequest,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.save(bookRequest, connectedUser), HttpStatus.CREATED);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.findAllProducts(page, size, connectedUser), HttpStatus.OK);
    }

    @GetMapping("/{owner}")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.findBooksByOwner(page, size,connectedUser), HttpStatus.OK);
    }
}
