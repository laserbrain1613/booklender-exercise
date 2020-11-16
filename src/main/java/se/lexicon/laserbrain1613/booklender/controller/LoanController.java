package se.lexicon.laserbrain1613.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.laserbrain1613.booklender.dto.LoanDto;
import se.lexicon.laserbrain1613.booklender.service.LoanService;

import java.util.List;

@RestController
public class LoanController {

    private final LoanService loanService; // final? (suggested by IntelliJ)

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> find() {
        List<LoanDto> result = loanService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> findById(@PathVariable("loanId") long loanId) {
        LoanDto result = loanService.findById(loanId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<LoanDto> create(@RequestBody LoanDto loanDto) {
        LoanDto result = loanService.create(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/") // Change path later, this will likely conflict with other controllers
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto loanDto) {
        LoanDto result = loanService.update(loanDto);
        return ResponseEntity.ok(result);
    }

}
