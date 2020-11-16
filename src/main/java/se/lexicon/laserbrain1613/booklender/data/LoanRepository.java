package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findAll();

    List<Loan> findAllByLoanTaker_UserId(int userId);

    List<Loan> findAllByBook_BookId(int bookId);

    List<Loan> findAllByTerminate(boolean terminate);

}