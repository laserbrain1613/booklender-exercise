package se.lexicon.laserbrain1613.booklender.util;

import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;
import se.lexicon.laserbrain1613.booklender.dto.LoanDto;
import se.lexicon.laserbrain1613.booklender.entity.Book;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

public class DTOConverterImpl implements DTOConverter {

    @Override
    public BookDto bookToDto(Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getMaxLoanDays(),
                book.getFinePerDay(),
                book.getDescription()
        );
    }

    @Override
    public Book dtoToBook(BookDto dto) {
        return new Book(
                dto.getTitle(),
                dto.getMaxLoanDays(),
                dto.getFinePerDay(),
                dto.getDescription()
        );
    }

    @Override
    public LibraryUserDto userToDto(LibraryUser user) {
        return new LibraryUserDto(
                user.getUserId(),
                user.getRegDate(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public LibraryUser dtoToUser(LibraryUserDto dto) {
        return new LibraryUser(
                dto.getRegDate(),
                dto.getName(),
                dto.getEmail()
        );
    }

    @Override
    public LoanDto loanToDto(Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                userToDto(loan.getLoanTaker()),
                bookToDto(loan.getBook()),
                loan.getLoanDate(),
                loan.isTerminate()
        );
    }

    @Override
    public Loan dtoToLoan(LoanDto dto) {
        return new Loan(
                dtoToUser(dto.getLoanTaker()),
                dtoToBook(dto.getBook()),
                dto.getLoanDate(),
                dto.isTerminate());
    }

}
