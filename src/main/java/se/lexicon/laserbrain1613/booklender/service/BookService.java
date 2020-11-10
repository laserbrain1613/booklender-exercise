package se.lexicon.laserbrain1613.booklender.service;

import se.lexicon.laserbrain1613.booklender.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    BookDto findById(int bookId);

    List<BookDto> findAll();

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    boolean delete(int bookId);
}
