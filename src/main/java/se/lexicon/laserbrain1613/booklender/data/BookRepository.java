package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

    List<Book> findAllByReserved(boolean reserved);

    List<Book> findAllByAvailable(boolean available);

    List<Book> findAllByTitleIgnoreCase(String title); // There is a slight chance two titles have the same name
}