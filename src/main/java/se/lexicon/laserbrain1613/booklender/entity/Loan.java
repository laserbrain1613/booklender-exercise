package se.lexicon.laserbrain1613.booklender.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH}
    )
    private LibraryUser loanTaker;
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH}
    )
    private Book book;
    private LocalDate loanDate;
    private boolean terminate; // 'terminated' is a reserved SQL word, unable to create table with that name

    public Loan() {
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminate) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminate = terminate;
    }

    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isOverdue() {
        if (isTerminate()) {
            return false;
        } else {
            LocalDate lastDate = this.loanDate.plusDays(book.getMaxLoanDays());
            return LocalDate.now().isAfter(lastDate);
        }
    }

    public BigDecimal getFine() {
        if (!isOverdue()) {
            return BigDecimal.valueOf(0);
        } else {
            LocalDate lastDate = this.loanDate.plusDays(book.getMaxLoanDays());
            return BigDecimal
                    .valueOf(lastDate.until(LocalDate.now(), ChronoUnit.DAYS))
                    .multiply(book.getFinePerDay());
        }
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminated) {
        this.terminate = terminated;
    }

    public boolean extendLoanDays(int days) {
        if (this.book.isReserved()) {
            return false;
        } else {
            this.book.setMaxLoanDays(this.book.getMaxLoanDays() + days);
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId &&
                terminate == loan.terminate &&
                Objects.equals(loanTaker, loan.loanTaker) &&
                Objects.equals(book, loan.book) &&
                Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, terminate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminate +
                '}';
    }

}