import { Component, OnInit } from '@angular/core';
import {
  BorrowedBookResponse,
  PageResponseBorrowedBookResponse,
} from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-borrowed-book-list',
  templateUrl: './borrowed-book-list.component.html',
  styleUrls: ['./borrowed-book-list.component.scss'],
})
export class BorrowedBookListComponent implements OnInit {
  page: number = 0;
  size: number = 5;
  constructor(private bookService: BookService) {}
  borrowedBooks: PageResponseBorrowedBookResponse = {};
  ngOnInit(): void {
    this.findAllBorrowedBooks();
  }
  findAllBorrowedBooks() {
    this.bookService
      .findAllBorrowedBooks({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (resp) => {
          this.borrowedBooks = resp;
        },
      });
  }
  returnBorrowedBook(book: BorrowedBookResponse) {
    throw new Error('Method not implemented.');
  }
  get isLastPage(): boolean {
    return this.page == (this.borrowedBooks.totalPages as number) - 1;
  }

  goToLastPage() {
    this.page = (this.borrowedBooks.totalPages as number) - 1;
    this.findAllBorrowedBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBorrowedBooks();
  }

  goToPage(pageIndex: number) {
    this.page = pageIndex;
    this.findAllBorrowedBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBorrowedBooks();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBorrowedBooks();
  }
}
