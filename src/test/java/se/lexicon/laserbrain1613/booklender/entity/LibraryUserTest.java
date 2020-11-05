package se.lexicon.laserbrain1613.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class LibraryUserTest {

    private LibraryUser testObject;

    @BeforeEach
    public void setUp() {
        testObject = new LibraryUser(LocalDate.now(), "test", "test@test.com");
    }

    @Test
    public void defaultConstructor_test() {
        //Arrange
        testObject = new LibraryUser();

        //Assert
        assertNull(testObject.getEmail());
        assertNull(testObject.getName());
        assertNull(testObject.getRegDate());
    }

    @Test
    public void getters_test() {
        assertEquals(0, testObject.getUserId());
        assertEquals(LocalDate.now(), testObject.getRegDate());
        assertEquals("test", testObject.getName());
        assertEquals("test@test.com", testObject.getEmail());
    }

    @Test
    public void setters_test() {
        //Act
        testObject.setName("test2");
        testObject.setEmail("test2@test2.com");

        //Assert
        assertEquals("test2", testObject.getName());
        assertEquals("test2@test2.com", testObject.getEmail());
    }

    @Test
    public void toString_test() {
        //Arrange
        String result = testObject.toString();

        //Assert
        assertTrue(result.contains(testObject.getRegDate().toString()));
        assertTrue(result.contains(testObject.getName()));
        assertTrue(result.contains(testObject.getEmail()));
    }

    @Test
    public void hashCode_test() {
        //Arrange
        LibraryUser result = new LibraryUser(
                testObject.getRegDate(),
                testObject.getName(),
                testObject.getEmail()
        );

        //Assert
        assertTrue(result.equals(testObject) && testObject.equals(result));
        assertEquals(testObject.hashCode(), result.hashCode());
    }

}
