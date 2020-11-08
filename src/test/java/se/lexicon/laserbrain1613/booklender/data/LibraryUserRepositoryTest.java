package se.lexicon.laserbrain1613.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class LibraryUserRepositoryTest {

    @Autowired
    LibraryUserRepository testObject;

    LibraryUser user;

    @BeforeEach
    void setUp() {
        testObject.save(new LibraryUser(LocalDate.now(), "test name 1", "test1@test.com"));
        user = testObject.save(new LibraryUser(LocalDate.now(), "test name 2", "test2@test.com"));
        testObject.save(new LibraryUser(LocalDate.now(), "test name 3", "test3@test.com"));
    }

    @Test
    void findByEmailIgnoreCase() {
        //Act
        LibraryUser result = testObject.findByEmailIgnoreCase("test2@test.com");

        //Assert
        assertEquals(result, user);
    }

}