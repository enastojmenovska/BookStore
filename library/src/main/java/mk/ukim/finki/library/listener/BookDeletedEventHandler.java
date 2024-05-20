package mk.ukim.finki.library.listener;

import mk.ukim.finki.library.model.event.BookDeletedEvent;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookDeletedEventHandler {
    private final BookService bookService;

    public BookDeletedEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookDeleted(BookDeletedEvent event) {
        System.out.println("Book deleted");
    }
}
