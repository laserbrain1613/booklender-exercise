package se.lexicon.laserbrain1613.booklender.service;

import se.lexicon.laserbrain1613.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {

    LoanDto findById(long loanId);

    List<LoanDto> findByBookId(int bookId);

    List<LoanDto> findByUserId(int userId);

    List<LoanDto> findByTerminate(boolean terminate);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(int loanId);
}
