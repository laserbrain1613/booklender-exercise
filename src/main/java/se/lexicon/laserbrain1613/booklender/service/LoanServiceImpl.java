package se.lexicon.laserbrain1613.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.laserbrain1613.booklender.data.BookRepository;
import se.lexicon.laserbrain1613.booklender.data.LibraryUserRepository;
import se.lexicon.laserbrain1613.booklender.data.LoanRepository;
import se.lexicon.laserbrain1613.booklender.dto.LoanDto;
import se.lexicon.laserbrain1613.booklender.entity.Loan;
import se.lexicon.laserbrain1613.booklender.util.DTOConverter;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    LoanRepository loanRepository;
    BookRepository bookRepository;
    LibraryUserRepository libraryUserRepository;
    DTOConverter dtoConverter;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository, DTOConverter dtoConverter) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public LoanDto findById(long loanId) {
        if (!loanRepository.findById(loanId).isPresent()) {
            throw new IllegalArgumentException("ERROR: Loan ID not found.");
        } else {
            return dtoConverter.loanToDto(loanRepository.findById(loanId).get());
        }
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        List<Loan> result = loanRepository.findAllByBook_BookId(bookId);
        return dtoConverter.loanToDto(result);
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        List<Loan> result = loanRepository.findAllByLoanTaker_UserId(userId);
        return dtoConverter.loanToDto(result);
    }

    @Override
    public List<LoanDto> findByTerminate(boolean terminate) {
        List<Loan> result = loanRepository.findAllByTerminate(terminate);
        return dtoConverter.loanToDto(result);
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> result = loanRepository.findAll();
        return dtoConverter.loanToDto(result);
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if (loanRepository.findById(loanDto.getLoanId()).isPresent()) {
            throw new IllegalArgumentException("ERROR: Loan ID already exists. Can't create new loan.");
        } else {
            Loan result = dtoConverter.dtoToLoan(loanDto);
            result = loanRepository.save(result);
            return dtoConverter.loanToDto(result);
        }
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        if (!loanRepository.findById(loanDto.getLoanId()).isPresent()) {
            throw new IllegalArgumentException("ERROR: Loan ID does not exist. Unable to update loan.");
        } else {
            Loan result = dtoConverter.dtoToLoan(loanDto);
            result = loanRepository.save(result);
            return dtoConverter.loanToDto(result);
        }
    }

    @Override
    public boolean delete(long loanId) {
        if (!loanRepository.findById(loanId).isPresent()) {
            throw new IllegalArgumentException("ERROR: Loan ID does not exist. Unable to delete loan.");
        } else {
            loanRepository.delete(loanRepository.findById(loanId).get());
            return true;
        }
    }

}
