package se.lexicon.laserbrain1613.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    private BookDto testObject;

    @BeforeEach
    void setUp() {
        testObject = new BookDto(1337, "test", 5, TEN, "test description");
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new BookDto();

        //Assert
        assertEquals(0, testObject.getBookId());
        assertNull(testObject.getTitle());
        assertEquals(0, testObject.getMaxLoanDays());
        assertNull(testObject.getFinePerDay());
        assertNull(testObject.getDescription());
    }

    @Test
    public void getters_test() {
        assertEquals(1337, testObject.getBookId());
        assertEquals("test", testObject.getTitle());
        assertFalse(testObject.isAvailable());
        assertFalse(testObject.isReserved());
        assertEquals(5, testObject.getMaxLoanDays());
        assertEquals(TEN, testObject.getFinePerDay());
        assertEquals("test description", testObject.getDescription());
    }

    @Test
    public void setters_test() {
        //Arrange
        testObject.setBookId(9000);
        testObject.setAvailable(true);
        testObject.setReserved(true);
        testObject.setMaxLoanDays(Integer.MAX_VALUE);
        testObject.setDescription("test description 2");
        testObject.setFinePerDay(ONE);
        testObject.setTitle("test title");

        //Assert
        assertEquals(9000, testObject.getBookId());
        assertTrue(testObject.isAvailable());
        assertTrue(testObject.isReserved());
        assertEquals(Integer.MAX_VALUE, testObject.getMaxLoanDays());
        assertEquals("test description 2", testObject.getDescription());
        assertEquals(ONE, testObject.getFinePerDay());
        assertEquals("test title", testObject.getTitle());
    }

    @Test
    void testHashCode() {
        //Arrange
        BookDto result = new BookDto(
                testObject.getBookId(),
                testObject.getTitle(),
                testObject.getMaxLoanDays(),
                testObject.getFinePerDay(),
                testObject.getDescription()
        );

        //Assert
        assertTrue(result.equals(testObject) && testObject.equals(result));
        assertEquals(testObject.hashCode(), result.hashCode());
    }

    @Test
    void testToString() {
        //Arrange
        String result = testObject.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testObject.getBookId())));
        assertTrue(result.contains(testObject.getTitle()));
        assertTrue(result.contains("available=false"));
        assertTrue(result.contains("reserved=false"));
        assertTrue(result.contains(Integer.toString(testObject.getMaxLoanDays())));
        assertTrue(result.contains(testObject.getFinePerDay().toString()));
        assertTrue(result.contains(testObject.getDescription()));
    }

}