package se.lexicon.laserbrain1613.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book testObject;

    @BeforeEach
    void setUp() {
        testObject = new Book("test", 5, TEN, "test description");
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new Book();

        //Assert
        assertNull(testObject.getTitle());
        assertEquals(0, testObject.getMaxLoanDays());
        assertNull(testObject.getFinePerDay());
        assertNull(testObject.getDescription());
    }

    @Test
    public void getters_test() {
        assertEquals(0, testObject.getBookId());
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
        testObject.setAvailable(true);
        testObject.setReserved(true);
        testObject.setMaxLoanDays(Integer.MAX_VALUE);
        testObject.setDescription("test description 2");
        testObject.setFinePerDay(ONE);
        testObject.setTitle("test title");

        //Assert
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
        Book result = new Book(
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
        assertTrue(result.contains(testObject.getTitle()));
        assertTrue(result.contains("available=false"));
        assertTrue(result.contains("reserved=false"));
        assertTrue(result.contains(Integer.toString(testObject.getMaxLoanDays())));
        assertTrue(result.contains(testObject.getFinePerDay().toString()));
        assertTrue(result.contains(testObject.getDescription()));
    }

}