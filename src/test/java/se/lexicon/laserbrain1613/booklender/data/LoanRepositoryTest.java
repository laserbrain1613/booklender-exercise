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
    public void setUp() throws Exception {
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
        List<Loan> result = testObject.findAllByLoanTakerUserId(user1.getUserId()); // Borrowed book 1 and 2
        List<Loan> result2 = testObject.findAllByLoanTakerUserId(user2.getUserId()); // Did not borrow any books
        List<Loan> result3 = testObject.findAllByLoanTakerUserId(user3.getUserId()); // Borrowed book 3

        //Assert
        assertEquals(2, result.size());
        assertEquals(0, result2.size());
        assertTrue(result2.isEmpty());
        assertEquals(1, result3.size());
    }


    @Test
    public void findByBookBookId() {
        //Act
        Loan result = testObject.findByBookBookId(book1.getBookId());
        Loan result2 = testObject.findByBookBookId(book2.getBookId());
        Loan result3 = testObject.findByBookBookId(book3.getBookId());
        Loan result4 = testObject.findByBookBookId(book4.getBookId());

        //Assert
        assertEquals(user1, result.getLoanTaker()); // user1 borrowed book1
        assertEquals(user1, result2.getLoanTaker()); // user1 borrowed book2
        assertEquals(user3, result3.getLoanTaker()); // user3 borrowed book3
        assertNull(result4); // no one has borrowed book4
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