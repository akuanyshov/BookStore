package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Book;

import java.util.ArrayList;

public interface BookService {
    ArrayList<Book> getAllBooks();
    void saveCourse (Book book);
    Book getBookById(Long id);
    Book saveBook(Book books);
}
