package se.lexicon.laserbrain1613.booklender.service;

import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(int userId);

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto dto);

    LibraryUserDto update(LibraryUserDto dto);

    boolean delete(int userId);
}
