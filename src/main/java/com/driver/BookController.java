package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(this.id);
        bookList.add(book);
        this.id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id")
    public ResponseEntity<Book> getBookById(@PathVariable("id")int id) {
        for(Book book : bookList) {
            if(book.getId()==id) return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteBookById(@PathVariable("id")int id) {
        for(Book book : bookList) {
            if(book.getId()==id) {
                bookList.remove(book);
            }
        }
    }
    // get request /get-all-books
    @GetMapping("/get-all-books")
    public List<Book> getAllBooks() {
        return bookList;
    }

    // delete request /delete-all-books
    @DeleteMapping("/delete-all-books")
    public void deleteAllBooks() {
        bookList.clear();
        this.id = 1;
    }

    // get request /get-books-by-author
    @GetMapping("/get-books-by-author")
    // pass author name as request param
    public Book getBooksByAuthor(@RequestParam("author")String author) {
        for(Book book : bookList) {
            if(book.getAuthor().equals(author)) return book;
        }
        return null;
    }

    // get request /get-books-by-genre
    @GetMapping("/get-books-by-genre")
    // pass genre name as request param
    public Book getBooksByGenre(@RequestParam("genre")String genre) {
        for(Book book : bookList) {
            if(book.getGenre().equals(genre)) return book;
        }
        return null;
    }
}