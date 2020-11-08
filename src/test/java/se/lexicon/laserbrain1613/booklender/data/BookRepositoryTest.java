package se.lexicon.laserbrain1613.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private Book testObject = new Book();

    @BeforeEach
    void setUp() {
        testObject = new Book();
    }

    @Test
    void findByReserved() {
    }

    @Test
    void findByAvailable() {
    }

    @Test
    void findByTitleIgnoreCase() {
    }
}