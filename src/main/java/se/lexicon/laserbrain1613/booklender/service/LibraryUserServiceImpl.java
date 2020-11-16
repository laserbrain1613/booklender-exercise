package se.lexicon.laserbrain1613.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.laserbrain1613.booklender.data.LibraryUserRepository;
import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;
import se.lexicon.laserbrain1613.booklender.entity.LibraryUser;
import se.lexicon.laserbrain1613.booklender.util.DTOConverter;

import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    LibraryUserRepository libraryUserRepository;
    DTOConverter dtoConverter;

    @Autowired
    public LibraryUserServiceImpl(LibraryUserRepository libraryUserRepository, DTOConverter dtoConverter) {
        this.libraryUserRepository = libraryUserRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public LibraryUserDto findById(int userId) {
        if (!libraryUserRepository.findById(userId).isPresent()) {
            throw new IllegalArgumentException("ERROR: User ID not found.");
        } else {
            return dtoConverter.userToDto(libraryUserRepository.findById(userId).get());
        }
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        if (libraryUserRepository.findByEmailIgnoreCase(email) == null) {
            throw new IllegalArgumentException("ERROR: E-mail not found.");
        } else {
            return dtoConverter.userToDto(libraryUserRepository.findByEmailIgnoreCase(email));
        }
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> result = libraryUserRepository.findAll();
        return dtoConverter.userToDto(result);
    }

    @Override
    public LibraryUserDto create(LibraryUserDto dto) {
        if (libraryUserRepository.findById(dto.getUserId()).isPresent()) {
            throw new IllegalArgumentException(("ERROR: User ID already exists. Can't create new user."));
        } else {
            LibraryUser result = dtoConverter.dtoToUser(dto);
            result = libraryUserRepository.save(result);
            return dtoConverter.userToDto(result);
        }
    }

    @Override
    public LibraryUserDto update(LibraryUserDto dto) {
        if (!libraryUserRepository.findById(dto.getUserId()).isPresent()) {
            throw new IllegalArgumentException("ERROR: User ID does not exist. Unable to update user.");
        }
        LibraryUser result = dtoConverter.dtoToUser(dto);
        result = libraryUserRepository.save(result);
        return dtoConverter.userToDto(result);
    }

    @Override
    public boolean delete(int userId) {
        if (!libraryUserRepository.findById(userId).isPresent()) {
            throw new IllegalArgumentException("ERROR: User ID does not exist. Unable to delete user.");
        } else {
            libraryUserRepository.delete(libraryUserRepository.findById(userId).get());
            return true;
        }
    }

}