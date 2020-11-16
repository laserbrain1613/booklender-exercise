package se.lexicon.laserbrain1613.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.laserbrain1613.booklender.entity.Book;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository testObject;

    Book book1;
    Book book2;
    Book book3;
    Book book4;
    LibraryUser user1;
    LibraryUser user2;
    LibraryUser user3;
    Loan loan1;
    Loan loan2;
    Loan loan3;

    @BeforeEach
    public void setUp() {
        book1 = new Book("Test 1", 10, BigDecimal.valueOf(100), "test1");
        book2 = new Book("Test 2", 20, BigDecimal.valueOf(200), "test2");
        book3 = new Book("Test 3", 30, BigDecimal.valueOf(300), "test3");
        book4 = new Book("Test 4", 40, BigDecimal.valueOf(400), "test4");
        user1 = new LibraryUser(LocalDate.now(), "test name 1", "test1@test.com");
        user2 = new LibraryUser(LocalDate.now(), "test name 2", "test2@test.com");
        user3 = new LibraryUser(LocalDate.now(), "test name 3", "test3@test.com");
        loan1 = testObject.save(new Loan(user1, book1, LocalDate.now(), true));
        loan2 = testObject.save(new Loan(user1, book2, LocalDate.now(), false));
        loan3 = testObject.save(new Loan(user3, book3, LocalDate.now(), false));
    }

    @Test
    public void findByLoanTakerUserId() {
        //Act
        List<Loan> result = testObject.findAllByLoanTaker_UserId(user1.getUserId()); // Borrowed book 1 and 2
        List<Loan> result2 = testObject.findAllByLoanTaker_UserId(user2.getUserId()); // Did not borrow any books
        List<Loan> result3 = testObject.findAllByLoanTaker_UserId(user3.getUserId()); // Borrowed book 3

        //Assert
        assertTrue(result.contains(loan1));
        assertTrue(result.contains(loan2));
        assertFalse(result.contains(loan3));
        assertFalse(result3.contains(loan1));
        assertFalse(result3.contains(loan2));
        assertTrue(result3.contains(loan3));
        assertEquals(2, result.size());
        assertEquals(0, result2.size());
        assertEquals(1, result3.size());
        assertTrue(result2.isEmpty());
    }

    @Test
    public void findByBookBookId() {
        //Act
        List<Loan> result = testObject.findAllByBook_BookId(book1.getBookId());
        List<Loan> result2 = testObject.findAllByBook_BookId(book2.getBookId());
        List<Loan> result3 = testObject.findAllByBook_BookId(book3.getBookId());
        List<Loan> result4 = testObject.findAllByBook_BookId(book4.getBookId());

        //Assert
        assertTrue(result.contains(loan1));
        assertTrue(result2.contains(loan2));
        assertTrue(result3.contains(loan3));
        assertTrue(result4.isEmpty());
    }

    @Test
    public void findAllByTerminate() {
        //Act
        List<Loan> result1 = testObject.findAllByTerminate(true);
        List<Loan> result2 = testObject.findAllByTerminate(false);

        //Assert
        assertTrue(result1.contains(loan1));
        assertEquals(1, result1.size());
        assertTrue(result2.contains(loan2));
        assertTrue(result2.contains(loan3));
        assertEquals(2, result2.size());
    }

}