package se.lexicon.laserbrain1613.booklender.util;

import org.springframework.stereotype.Service;
import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;
import se.lexicon.laserbrain1613.booklender.dto.LoanDto;
import se.lexicon.laserbrain1613.booklender.entity.Book;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOConverterImpl implements DTOConverter {

    @Override
    public BookDto bookToDto(Book book) { // Single object
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getMaxLoanDays(),
                book.getFinePerDay(),
                book.getDescription()
        );
    }

    @Override
    public List<BookDto> bookToDto(List<Book> books) { // List of objects
        return books.stream()
                .map(this::bookToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Book dtoToBook(BookDto dto) { // Single object
        return new Book(
                dto.getTitle(),
                dto.getMaxLoanDays(),
                dto.getFinePerDay(),
                dto.getDescription()
        );
    }

    @Override
    public LibraryUserDto userToDto(LibraryUser user) { // Single object
        return new LibraryUserDto(
                user.getUserId(),
                user.getRegDate(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public List<LibraryUserDto> userToDto(List<LibraryUser> libraryUsers) { // List of objects
        return libraryUsers.stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryUser dtoToUser(LibraryUserDto dto) { // Single object
        return new LibraryUser(
                dto.getRegDate(),
                dto.getName(),
                dto.getEmail()
        );
    }

    @Override
    public LoanDto loanToDto(Loan loan) { // Single object
        return new LoanDto(
                loan.getLoanId(),
                userToDto(loan.getLoanTaker()),
                bookToDto(loan.getBook()),
                loan.getLoanDate(),
                loan.isTerminate()
        );
    }

    @Override
    public List<LoanDto> loanToDto(List<Loan> loans) { // List of objects
        return loans.stream()
                .map(this::loanToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Loan dtoToLoan(LoanDto dto) { // Single object
        return new Loan(
                dtoToUser(dto.getLoanTaker()),
                dtoToBook(dto.getBook()),
                dto.getLoanDate(),
                dto.isTerminate());
    }

}
