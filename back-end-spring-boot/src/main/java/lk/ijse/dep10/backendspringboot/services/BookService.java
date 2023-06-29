package lk.ijse.dep10.backendspringboot.services;

import lk.ijse.dep10.backendspringboot.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book saveCustomer(Book book) throws InterruptedException;
}
