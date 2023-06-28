package lk.ijse.dep10.backendspringboot.dao.impl;

import lk.ijse.dep10.backendspringboot.dao.BookDAO;
import lk.ijse.dep10.backendspringboot.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Component
public class BookDAOImpl implements BookDAO {


    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> Book.builder().isbn(rs.getString("isbn"))
                       .title(rs.getString("title")).build();



    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", BOOK_ROW_MAPPER);
    }

    @Override
    public String save(Book book) {
        jdbcTemplate.update(con -> {
            PreparedStatement stm = con.prepareStatement
                    ("INSERT INTO book (isbn, title) VALUES (?, ?)");
            stm.setString(1, book.getIsbn());
            stm.setString(2, book.getTitle());
            return stm;
        });
        return "Okey";
    }
}
