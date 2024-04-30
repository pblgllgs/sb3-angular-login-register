import { Component, Input } from '@angular/core';
import { BookResponse } from 'src/app/services/models';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent {
  private _book: BookResponse = {};
  private _bookCover: string | undefined;

  public get book(): BookResponse {
    return this._book;
  }
  @Input()
  public set book(value: BookResponse) {
    this._book = value;
  }

  public get bookCover(): string | undefined {
    if(this._book.cover){
      return 'data:image/jpg;base64, ' + this._book.cover;
    }
    return 'https://media.istockphoto.com/id/1409329028/vector/no-picture-available-placeholder-thumbnail-icon-illustration-design.jpg?s=612x612&w=0&k=20&c=_zOuJu755g2eEUioiOUdz_mHKJQJn-tDgIAhQzyeKUQ=';
  }
  public set bookCover(value: string | undefined) {
    this._bookCover = value;
  }

}
