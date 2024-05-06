import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  BookResponse,
  PageResponseBookResponse,
} from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
})
export class BookListComponent implements OnInit {
  page = 0;
  size = 4;
  bookResponse: PageResponseBookResponse = {};
  message: string = '';
  level = 'success';

  constructor(private bookService: BookService, private router: Router) {}
  ngOnInit(): void {
    this.findAllBooks();
  }
  findAllBooks() {
    this.bookService
      .findAllBooks({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (books) => {
          this.bookResponse = books;
        },
      });
  }
  borrowBook(book: BookResponse) {
    this.message = '';
    this.bookService
      .borrowBook({
        bookId: book.id as number,
      })
      .subscribe({
        next: () => {
          this.level = 'success';
          this.message = 'Book succesfully added to your list';
        },
        error : (err) => {
            console.log(err);
            this.level = 'error';
            this.message = err.error.error;
        }
      });
  }
  get isLastPage(): boolean {
    return this.page == (this.bookResponse.totalPages as number) - 1;
  }
  goToLastPage() {
    this.page = (this.bookResponse.totalPages as number) - 1;
    this.findAllBooks();
  }
  goToNextPage() {
    this.page++;
    this.findAllBooks();
  }
  goToPage(pageIndex: number) {
    this.page = pageIndex;
    this.findAllBooks();
  }
  goToPreviousPage() {
    this.page--;
    this.findAllBooks();
  }
  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }
}
