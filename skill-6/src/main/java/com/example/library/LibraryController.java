package com.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LibraryController {

    private final Map<Integer, Book> catalog = Map.of(
            1, new Book(1, "Clean Code", "Robert C. Martin", 39.99),
            2, new Book(2, "Effective Java", "Joshua Bloch", 49.99),
            3, new Book(3, "Domain-Driven Design", "Eric Evans", 59.99));

    private final List<Book> storedBooks = new CopyOnWriteArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(100);

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Library API";
    }

    @GetMapping("/count")
    public int totalBooks() {
        return catalog.size() + storedBooks.size();
    }

    @GetMapping("/price")
    public double samplePrice() {
        return 45.50;
    }

    @GetMapping("/books")
    public List<String> listBookTitles() {
        return catalog.values()
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return storedBooks.stream()
                .filter(book -> book.getId() != null && book.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    Book catalogBook = catalog.get(id);
                    if (catalogBook != null) {
                        return catalogBook;
                    }
                    throwNotFound(id);
                    return null; // unreachable but satisfies compiler
                });
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam String title) {
        return "Search request received for title: " + title;
    }

    @GetMapping("/author/{name}")
    public String authorInfo(@PathVariable String name) {
        return "Author highlight: " + name;
    }

    @PostMapping("/addbook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.incrementAndGet());
        }
        storedBooks.add(book);
        return book;
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return new ArrayList<>(storedBooks);
    }

    private void throwNotFound(int id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Book with id " + id + " not found");
    }
}
