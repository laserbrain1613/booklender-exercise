package se.lexicon.laserbrain1613.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository testObject;
    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void setUp() {
        book1 = testObject.save(new Book("Test 1", 10, BigDecimal.valueOf(100), "test1"));
        book2 = testObject.save(new Book("Test 2", 20, BigDecimal.valueOf(200), "test2"));
        book3 = testObject.save(new Book("Test 3", 30, BigDecimal.valueOf(300), "test3"));
    }

    @Test
    void findByReserved() {
        //Arrange
        book1.setReserved(false);
        book2.setReserved(true);
        book3.setReserved(true);

        //Act
        List<Book> result = testObject.findAllByReserved(true);
        List<Book> result2 = testObject.findAllByReserved(false);

        //Assert
        assertFalse(result.contains(book1));
        assertTrue(result.contains(book2));
        assertTrue(result.contains(book3));
        assertTrue(result2.contains(book1));
        assertFalse(result2.contains(book2));
        assertFalse(result2.contains(book2));
    }

    @Test
    void findByAvailable() {
        //Arrange
        book1.setAvailable(false);
        book2.setAvailable(true);
        book3.setAvailable(true);

        //Act
        List<Book> result = testObject.findAllByAvailable(true);
        List<Book> result2 = testObject.findAllByAvailable(false);

        //Assert
        assertFalse(result.contains(book1));
        assertTrue(result.contains(book2));
        assertTrue(result.contains(book3));
        assertTrue(result2.contains(book1));
        assertFalse(result2.contains(book2));
        assertFalse(result2.contains(book2));
    }

    @Test
    void findByTitleIgnoreCase() {
        //Arrange
        book1.setTitle("Life After Life");
        book2.setTitle("Life After Life");
        book3.setTitle("Half-Life 2 - The True Story");

        //Act
        List<Book> result = testObject.findAllByTitleIgnoreCase("Life After Life");

        //Assert
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
        assertFalse(result.contains(book3));
        assertEquals(2, result.size());
    }
}