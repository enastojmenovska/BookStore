package mk.ukim.finki.library.model.event;

import mk.ukim.finki.library.model.Book;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class BookEditedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public BookEditedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public BookEditedEvent(Book source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return this.when;
    }
}
