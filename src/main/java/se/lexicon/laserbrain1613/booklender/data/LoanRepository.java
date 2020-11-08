package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Integer> {

    Loan findByLoanId(long loanId);
    Loan findByBook_BookId(int bookId);
    List<Loan> findAllByTerminate(boolean terminate);
}