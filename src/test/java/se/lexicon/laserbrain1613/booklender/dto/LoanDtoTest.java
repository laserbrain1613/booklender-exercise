package se.lexicon.laserbrain1613.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.*;

class LoanDtoTest {

    private LoanDto testObject;
    private LibraryUserDto testUser;
    private BookDto testBook;

    @BeforeEach
    void setUp() {
        testUser = new LibraryUserDto();
        testBook = new BookDto();
        testObject = new LoanDto(1337, testUser, testBook, LocalDate.now(), false);
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new LoanDto();

        //Assert
        assertEquals(0L, testObject.getLoanId());
        assertNull(testObject.getLoanTaker());
        assertNull(testObject.getBook());
        assertNull(testObject.getLoanDate());
        assertFalse(testObject.isTerminate());
    }

    @Test
    public void getters_test() {
        assertEquals(1337, testObject.getLoanId());
        assertEquals(testUser, testObject.getLoanTaker());
        assertEquals(testBook, testObject.getBook());
        assertFalse(testObject.isTerminate());
        assertEquals(LocalDate.now(), testObject.getLoanDate());
    }

    @Test
    public void setters_test() {
        //Arrange
        BookDto book = new BookDto(4096, "stuff", 50, TEN, "stuff description");
        LibraryUserDto libraryUser = new LibraryUserDto(8192, LocalDate.now(), "test", "test@test.com");

        //Act
        testObject.setLoanId(16384);
        testObject.setLoanDate(LocalDate.now().plusDays(42));
        testObject.setBook(book);
        testObject.setLoanTaker(libraryUser);
        testObject.setTerminate(true);

        //Assert
        assertEquals(16384, testObject.getLoanId());
        assertEquals(LocalDate.now().plusDays(42), testObject.getLoanDate());
        assertEquals(book, testObject.getBook());
        assertEquals(libraryUser, testObject.getLoanTaker());
        assertTrue(testObject.isTerminate());
    }


    @Test
    void testHashCode() {
        //Arrange
        LoanDto result = new LoanDto(
                testObject.getLoanId(),
                testUser,
                testBook,
                testObject.getLoanDate(),
                testObject.isTerminate()
        );

        //Assert
        assertTrue(result.equals(testObject) && testObject.equals(result));
        assertEquals(testObject.hashCode(), result.hashCode());
    }

    @Test
    void testToString() {
        //Arrange
        testBook = new BookDto(31415, "testtitle", 5, TEN, "test description");
        testUser = new LibraryUserDto(926535, LocalDate.now(), "testuser", "test@test.com");
        testObject = new LoanDto(897932, testUser, testBook, LocalDate.now().plusDays(5), true);
        String result = testObject.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testObject.getLoanId())));
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