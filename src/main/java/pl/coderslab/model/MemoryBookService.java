package pl.coderslab.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemoryBookService {
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book("9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book("9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book("9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public Book getBookById(long id) {
        return getList().stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public List<Book> updateBook(Book book){
        Book bookById = getBookById(book.getId());
        list.remove(bookById);
        list.add(book);
        return list;
    }

    public void addBook(Book book){
        list.add(book);
    }
}