package lk.ijse.dep10.backendspringboot.api;

import lk.ijse.dep10.backendspringboot.entity.Book;
import lk.ijse.dep10.backendspringboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BooksHttpController {
    private final BookService bookService;

    public BooksHttpController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllCustomers(){
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book saveCustomer(@RequestBody Book book) throws InterruptedException {
        return bookService.saveCustomer(book);
    }

}
