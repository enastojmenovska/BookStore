package mk.ukim.finki.library.listener;

import mk.ukim.finki.library.model.event.BookEditedEvent;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEditedEventHandler {
    private final BookService bookService;

    public BookEditedEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookDeleted(BookEditedEvent event) {
        System.out.println("Book is edited");
    }
}