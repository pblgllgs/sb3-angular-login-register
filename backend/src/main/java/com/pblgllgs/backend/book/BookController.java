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

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.findAllBooksByOwner(page, size, connectedUser), HttpStatus.OK);
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.findAllBorrowedBooks(page, size, connectedUser), HttpStatus.OK);
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.findAllReturnedBooks(page, size, connectedUser), HttpStatus.OK);
    }

    @PatchMapping("/sharable/{bookId}")
    public ResponseEntity<Integer> updateShareableStatus(
            @PathVariable("bookId") Integer bookId,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.updateShareableStatus(bookId, connectedUser), HttpStatus.OK);
    }

    @PatchMapping("/archived/{bookId}")
    public ResponseEntity<Integer> updateArchivedStatus(
            @PathVariable("bookId") Integer bookId,
            Authentication connectedUser
    ) {
        return new ResponseEntity<>(bookService.updateArchiveStatus(bookId, connectedUser), HttpStatus.OK);
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<Integer> borrowBook(
            @PathVariable("bookId") Integer bookId,
                    Authentication connectedUser
            ){
        return new ResponseEntity<>(bookService.borrowBook(bookId,connectedUser), HttpStatus.OK);
    }

    @PatchMapping("/borrow/return/{bookId}")
    public ResponseEntity<Integer> returnBorrowBook(
            @PathVariable("bookId") Integer bookId,
            Authentication connectedUser
    ){
        return new ResponseEntity<>(bookService.returnBorrowBook(bookId,connectedUser), HttpStatus.OK);
    }

}
