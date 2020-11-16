package se.lexicon.laserbrain1613.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.laserbrain1613.booklender.dto.LibraryUserDto;
import se.lexicon.laserbrain1613.booklender.service.LibraryUserService;

import java.util.List;

@RestController
public class LibraryUserController {

    private final LibraryUserService libraryUserService; // Is final ok? (IntelliJ suggestion)

    @Autowired
    public LibraryUserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("userId") int userId) { // Was this supposed to be List?
        LibraryUserDto result = libraryUserService.findById(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{email}")
    public ResponseEntity<LibraryUserDto> findByEmailIgnoreCase(@PathVariable("email") String email) { // Remove IgnoreCase from repo?
        LibraryUserDto result = libraryUserService.findByEmail(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        List<LibraryUserDto> result = libraryUserService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto libraryUserDto) {
        LibraryUserDto result = libraryUserService.create(libraryUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto libraryUserDto) {
        LibraryUserDto result = libraryUserService.create(libraryUserDto);
        return ResponseEntity.ok(result);
    }

}