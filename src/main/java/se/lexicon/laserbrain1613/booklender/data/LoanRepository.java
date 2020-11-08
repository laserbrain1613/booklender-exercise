package se.lexicon.laserbrain1613.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Integer> {

    List<Loan> findAllByLoanTakerUserId(int userId);

    Loan findByBookBookId(int bookId);

    List<Loan> findAllByTerminate(boolean terminate);
}