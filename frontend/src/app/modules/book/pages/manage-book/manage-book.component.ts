import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookRequest } from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-manage-book',
  templateUrl: './manage-book.component.html',
  styleUrls: ['./manage-book.component.scss'],
})
export class ManageBookComponent {
  constructor(private bookService: BookService, private router: Router) {}

  errorMsg: Array<string> = [];
  selectedPicture: string | undefined;
  selectedBookCover: any;
  bookRequest: BookRequest = {
    authorName: '',
    isbn: '',
    synopsis: '',
    title: '',
  };

  onFileSelected(event: any) {
    this.selectedBookCover = event.target.files[0];
    console.log(this.selectedBookCover);
    if (this.selectedBookCover) {
      const reader: FileReader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      };
      reader.readAsDataURL(this.selectedBookCover);
    }
  }
  sabeBook() {
    this.bookService
      .createBook({
        body: this.bookRequest,
      })
      .subscribe({
        next: (bookId) => {
          this.bookService
            .uploadBookCoverPicture({
              bookId: bookId,
              body: {
                file: this.selectedBookCover,
              },
            })
            .subscribe({
              next: () => {
                this.router.navigate(['/books/my-books']);
              },
            });
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        } 
      });
  }
}
