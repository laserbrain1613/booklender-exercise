package se.lexicon.laserbrain1613.booklender.util;

import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;
import se.lexicon.laserbrain1613.booklender.dto.LoanDto;
import se.lexicon.laserbrain1613.booklender.entity.Book;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;
import se.lexicon.laserbrain1613.booklender.entity.Loan;

public interface DTOConverter {

    BookDto bookToDto(Book book);

    Book dtoToBook(BookDto dto);

    LibraryUserDto userToDto(LibraryUser user);

    LibraryUser dtoToUser(LibraryUserDto dto);

    LoanDto loanToDto(Loan loan);

    Loan dtoToLoan(LoanDto dto);
}
