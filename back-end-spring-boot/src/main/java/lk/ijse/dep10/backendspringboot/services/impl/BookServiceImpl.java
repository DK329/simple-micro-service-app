package lk.ijse.dep10.backendspringboot.services.impl;

import lk.ijse.dep10.backendspringboot.dao.BookDAO;
import lk.ijse.dep10.backendspringboot.entity.Book;
import lk.ijse.dep10.backendspringboot.services.BookService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;


    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    @Transactional
    public Book saveCustomer(Book book) throws InterruptedException {
        String saveBook=bookDAO.save(book);
        return book;
    }


}
