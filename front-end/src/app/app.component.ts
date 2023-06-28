import { Component } from '@angular/core';
import {Book} from "./dto/book";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front-end';

  bookList:Array<Book> =[];
  http:HttpClient;

  constructor(http:HttpClient) {
    this.http=http;
    http.get<Array<Book>>('http://localhost:8080/api/v1/books').subscribe(bookList=>{
      this.bookList=bookList;
    })
  }

  addBook(isbn: HTMLInputElement, title: HTMLInputElement) {
    if (!isbn.value.trim()) {
      isbn.select();
      return;
    }
    if (!title.value.trim()) {
      title.select();
      return;
    }
    this.http.post<Book>('http://localhost:8080/api/v1/books', new Book(isbn.value, title.value))
      .subscribe(book => {
        this.bookList.push(book);
        isbn.value = '';
        title.value = '';
        isbn.focus();
      })
  }
  deleteBook(book: Book) {
    this.http.delete(`http://localhost:8080/api/v1/books/${book.isbn}`).subscribe(data => {
      const index: number = this.bookList.indexOf(book);
      this.bookList.splice(index, 1)
    })

  }

  updateBook(isbn: HTMLInputElement, title: HTMLInputElement) {
    if (!isbn.value.trim()) {
      isbn.select();
      return;
    }
    if (!title.value.trim()) {
      title.select();
      return;
    }
    this.http.patch<Book>(`http://localhost:8080/api/v1/books/${isbn.value}`, new Book(isbn.value, title.value))
      .subscribe(book => {
        book.isbn=isbn.value;
        book.title=title.value;
        isbn.value = '';
        title.value = '';
        isbn.focus();
      })
  }
}
