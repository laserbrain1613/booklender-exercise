package se.lexicon.laserbrain1613.booklender.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {

    private long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;

    public Loan() {
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
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
        LocalDate lastDay = this.loanDate.plusDays(book.getMaxLoanDays());
        return LocalDate.now().isAfter(lastDay);
    }

    public BigDecimal getFine() {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastDay = this.loanDate.plusDays(book.getMaxLoanDays());
        long finePerDay = this.book.getFinePerDay().longValue();
        return BigDecimal.valueOf(currentDate.until(lastDay, ChronoUnit.DAYS) * finePerDay); // Will this return a negative fine? Needs testing
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
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
                terminated == loan.terminated &&
                Objects.equals(loanTaker, loan.loanTaker) &&
                Objects.equals(book, loan.book) &&
                Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, terminated);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminated +
                '}';
    }

}