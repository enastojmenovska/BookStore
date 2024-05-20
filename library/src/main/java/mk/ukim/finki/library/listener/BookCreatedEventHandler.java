package mk.ukim.finki.library.listener;

import mk.ukim.finki.library.model.event.BookCreatedEvent;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookCreatedEventHandler {
    private final BookService bookService;

    public BookCreatedEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreatedEvent event) {
        System.out.println("New book is created");
    }
}
