package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;

import java.util.List;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

    List<LibraryUser> findAll();

    LibraryUser findByEmailIgnoreCase(String email);
}