import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  BookResponse,
  PageResponseBookResponse,
} from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss'],
})
export class MyBooksComponent implements OnInit {
  page = 0;
  size = 3;
  bookResponse: PageResponseBookResponse = {};

  constructor(private bookService: BookService, private router: Router) {}
  ngOnInit(): void {
    this.findAllBooks();
  }
  private findAllBooks() {
    this.bookService
      .findAllBooksByOwner({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (books) => {
          this.bookResponse = books;
        },
      });
  }

  editBook(book: BookResponse) {
    this.router.navigate(['books', 'manage', book.id]);
  }

  shareBook(book: BookResponse) {
    this.bookService
      .updateShareableStatus({
        'bookId': book.id as number,
      })
      .subscribe({
        next: () => {
          book.shareable = !book.shareable;
        },
      });
  }

  archiveBook(book: BookResponse) {
    this.bookService
      .updateArchivedStatus({
        'bookId': book.id as number,
      })
      .subscribe({
        next: () => {
          book.archived = !book.archived;
        },
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
