package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.repositories.BookRepository;
import kz.special3.specialSecurity3.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public ArrayList<Book> getAllBooks() {
        return (ArrayList<Book>) bookRepository.findAll();
    }

    @Override
    public void saveCourse(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getOne(id);
    }
    @Override
    public Book saveBook(Book books) {
        return bookRepository.save(books);
    }
}
