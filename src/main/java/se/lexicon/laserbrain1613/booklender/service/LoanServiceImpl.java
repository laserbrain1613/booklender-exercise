package se.lexicon.laserbrain1613.booklender.service;

import se.lexicon.laserbrain1613.booklender.dto.LoanDto;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    @Override
    public LoanDto findById(long loanId) {
        return null;
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        return null;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        return null;
    }

    @Override
    public List<LoanDto> findByTerminate(boolean terminate) {
        return null;
    }

    @Override
    public List<LoanDto> findAll() {
        return null;
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        return null;
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        return null;
    }

    @Override
    public boolean delete(int loanId) {
        return false;
    }
}
