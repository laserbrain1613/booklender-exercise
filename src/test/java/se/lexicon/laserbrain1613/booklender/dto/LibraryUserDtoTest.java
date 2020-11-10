package se.lexicon.laserbrain1613.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUserDtoTest {

    private LibraryUserDto testObject;

    @BeforeEach
    public void setUp() {
        testObject = new LibraryUserDto(1337, LocalDate.now(), "test", "test@test.com");
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new LibraryUserDto();

        //Assert
        assertEquals(0, testObject.getUserId());
        assertNull(testObject.getEmail());
        assertNull(testObject.getName());
        assertNull(testObject.getRegDate());
    }

    @Test
    public void getters_test() {
        assertEquals(1337, testObject.getUserId());
        assertEquals(LocalDate.now(), testObject.getRegDate());
        assertEquals("test", testObject.getName());
        assertEquals("test@test.com", testObject.getEmail());
    }

    @Test
    public void setters_test() {
        //Act
        testObject.setUserId(9000);
        testObject.setRegDate(LocalDate.now().plusDays(42));
        testObject.setName("test2");
        testObject.setEmail("test2@test2.com");

        //Assert
        assertEquals(LocalDate.now().plusDays(42), testObject.getRegDate());
        assertEquals(9000, testObject.getUserId());
        assertEquals("test2", testObject.getName());
        assertEquals("test2@test2.com", testObject.getEmail());
    }

    @Test
    public void toString_test() {
        //Arrange
        String result = testObject.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testObject.getUserId())));
        assertTrue(result.contains(testObject.getRegDate().toString()));
        assertTrue(result.contains(testObject.getName()));
        assertTrue(result.contains(testObject.getEmail()));
    }

    @Test
    public void hashCode_test() {
        //Arrange
        LibraryUserDto result = new LibraryUserDto(
                testObject.getUserId(),
                testObject.getRegDate(),
                testObject.getName(),
                testObject.getEmail()
        );

        //Assert
        assertTrue(result.equals(testObject) && testObject.equals(result));
        assertEquals(testObject.hashCode(), result.hashCode());
    }

}