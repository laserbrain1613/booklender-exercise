package se.lexicon.laserbrain1613.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.laserbrain1613.booklender.data.BookRepository;
import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.entity.Book;
import se.lexicon.laserbrain1613.booklender.util.DTOConverter;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    DTOConverter dtoConverter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, DTOConverter dtoConverter) {
        this.bookRepository = bookRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        List<Book> result = bookRepository.findAllByReserved(reserved);
        return dtoConverter.bookToDto(result);
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        List<Book> result = bookRepository.findAllByAvailable(available);
        return dtoConverter.bookToDto(result);
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        List<Book> result = bookRepository.findAllByTitleIgnoreCase(title);
        return dtoConverter.bookToDto(result);
    }

    @Override
    public BookDto findById(int bookId) {
        if (!bookRepository.findById(bookId).isPresent()) {
            throw new IllegalArgumentException("ERROR: Book ID not found.");
        } else {
            return dtoConverter.bookToDto(bookRepository.findById(bookId).get());
        }
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> result = bookRepository.findAll();
        return dtoConverter.bookToDto(result);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookRepository.findById(bookDto.getBookId()).isPresent()) {
            throw new IllegalArgumentException("ERROR: Book ID already exists. Can't create new book.");
        }
        Book result = dtoConverter.dtoToBook(bookDto);
        result = bookRepository.save(result);
        return dtoConverter.bookToDto(result);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (!bookRepository.findById(bookDto.getBookId()).isPresent()) {
            throw new IllegalArgumentException("ERROR: Book ID does not exist. Unable to update book.");
        }
        Book result = dtoConverter.dtoToBook(bookDto);
        result = bookRepository.save(result);
        return dtoConverter.bookToDto(result);
    }

    @Override
    public boolean delete(int bookId) {
        if (!bookRepository.findById(bookId).isPresent()) {
            throw new IllegalArgumentException("ERROR: Book ID does not exist. Unable to delete book.");
        } else {
            bookRepository.delete(bookRepository.findById(bookId).get());
            return true;
        }
    }

}
