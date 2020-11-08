package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

    LibraryUser findByEmailIgnoreCase(String email);
}