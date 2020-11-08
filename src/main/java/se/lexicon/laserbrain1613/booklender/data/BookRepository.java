package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAllByReserved(boolean reserved);
    List<Book> findAllByAvailable(boolean available);
    List<Book> findAllByTitleIgnoreCase(String title);
}