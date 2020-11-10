package se.lexicon.laserbrain1613.booklender.service;

import se.lexicon.laserbrain1613.booklender.dto.BookDto;
import se.lexicon.laserbrain1613.booklender.entity.Book;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return null;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return null;
    }

    @Override
    public BookDto findById(int bookId) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;
    }

    @Override
    public boolean delete(int bookId) {
        return false;
    }

    public BookDto convertBookEntityToBookDto(Book book) {
        return null;
    }

    public Book convertBookDtoToBookEntity(BookDto dto) {
        return null;
    }

}
