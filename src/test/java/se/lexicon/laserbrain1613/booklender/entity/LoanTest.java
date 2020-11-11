package se.lexicon.laserbrain1613.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.TEN;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan testObject;
    private LibraryUser testUser;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testUser = new LibraryUser();
        testBook = new Book();
        testObject = new Loan(testUser, testBook, LocalDate.now(), false);
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new Loan();

        //Assert
        assertEquals(0L, testObject.getLoanId());
        assertNull(testObject.getLoanTaker());
        assertNull(testObject.getBook());
        assertNull(testObject.getLoanDate());
        assertFalse(testObject.isTerminate());
    }

    @Test
    public void getters_test() {
        assertEquals(0, testObject.getLoanId());
        assertEquals(testUser, testObject.getLoanTaker());
        assertEquals(testBook, testObject.getBook());
        assertFalse(testObject.isOverdue());
        assertFalse(testObject.isTerminate());
        assertEquals(LocalDate.now(), testObject.getLoanDate());
    }

    @Test
    public void setters_test() {
        //Arrange
        Book book = new Book("stuff", 50, TEN, "stuff description");
        LibraryUser libraryUser = new LibraryUser(LocalDate.now(), "test", "test@test.com");

        //Act
        testObject.setBook(book);
        testObject.setLoanTaker(libraryUser);
        testObject.setTerminate(true);

        //Assert
        assertEquals(book, testObject.getBook());
        assertEquals(libraryUser, testObject.getLoanTaker());
        assertTrue(testObject.isTerminate());
    }

    @Test
    public void isOverdue_true() {
        //Arrange
        testObject = new Loan(testUser, testBook, LocalDate.now().minusDays(5), false);

        //Assert
        assertTrue(testObject.isOverdue());
    }

    @Test
    public void isOverdue_false() {
        //Arrange
        testObject = new Loan(testUser, testBook, LocalDate.now().plusDays(5), false);

        //Assert
        assertFalse(testObject.isOverdue());
    }

    @Test
    public void isOverdue_sameDay() { // Same day means last day allowed to lend the book
        //Arrange
        testObject = new Loan(testUser, testBook, LocalDate.now(), false);

        //Assert
        assertFalse(testObject.isOverdue());
    }

    @Test
    public void getFine_test() {
        //Arrange
        testBook = new Book("test", 5, TEN, "test description");
        testObject = new Loan(testUser, testBook, LocalDate.now().minusDays(10), false);

        //Act
        BigDecimal result = testObject.getFine();

        //Assert
        assertEquals("50", result.toString()); // finePerDay (TEN) x 5 overdue days = 50
    }

    @Test
    public void extendLoanDays_isReserved() {
        //Arrange
        testBook.setReserved(true);

        //Act
        boolean result = testObject.extendLoanDays(5);

        //Assert
        assertFalse(result);
    }

    @Test
    public void extendLoanDays_isNotReserved() {
        //Act
        testBook.setReserved(false);

        //Act
        boolean result = testObject.extendLoanDays(5);

        //Assert
        assertTrue(result);
    }

    @Test
    public void extendLoanDays_countDays() {
        //Arrange
        int result = testBook.getMaxLoanDays();

        //Act
        testBook.setMaxLoanDays(testBook.getMaxLoanDays() + 5);

        //Act
        assertEquals(result + 5, testBook.getMaxLoanDays());
    }

    @Test
    void testHashCode() {
        //Arrange
        Loan result = new Loan(
                testUser,
                testBook,
                LocalDate.now(),
                false
        );

        //Assert
        assertTrue(result.equals(testObject) && testObject.equals(result));
        assertEquals(testObject.hashCode(), result.hashCode());
    }

    @Test
    void testToString() {
        //Arrange
        testBook = new Book("testtitle", 5, TEN, "test description");
        testUser = new LibraryUser(LocalDate.now(), "testuser", "test@test.com");
        testObject = new Loan(testUser, testBook, LocalDate.now().plusDays(5), true);
        String result = testObject.toString();

        //Assert
        assertTrue(result.contains(LocalDate.now().plusDays(5).toString()));
        assertTrue(result.contains("testuser"));
        assertTrue(result.contains("test@test.com"));
        assertTrue(result.contains("testtitle"));
        assertTrue(result.contains("reserved=false"));
        assertTrue(result.contains("terminated=true"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("10"));
   }

}