import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


import static exception.handling.Exercises.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionHandlingTest {
    private static int COUNT = 1;

    @BeforeEach
    void identify(){
        log.info("============================EXECUTING TEST "+COUNT+"===============================================");
    }

    @AfterEach
    void updateCount(){
        COUNT++;
    }

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingTest.class);

    @Test
    void exceptionIfNumberIsOddTest(){
        assertDoesNotThrow(() -> exceptionIfNumberIsOdd(2));
        assertThrows(IllegalArgumentException.class, () -> exceptionIfNumberIsOdd(11));
    }

    @Test
    void exceptionIfFileNotFoundTest() {
        log.info("Running test 1");
        assertDoesNotThrow(() -> exceptionIfFileNotFound(Path.of("src/main/java/exception/handling/input.txt")));

        log.info("Running test 2");
        assertThrows(IOException.class,() -> exceptionIfFileNotFound(Path.of("src/exception/handling/nonExistentFile.txt")));
    }

    @Test
    void exceptionIfNumberIsPositiveTest() {
        log.info("Running test 1");
        assertDoesNotThrow(() -> exceptionIfNumberIsPositive(Path.of("src/main/java/exception/handling/inputAllNegatives.txt")));

        log.info("Running test 2");
        assertThrows(IOException.class,() -> exceptionIfNumberIsPositive(Path.of("src/main/java/exception/handling/input.txt")));
    }

    @Test
    void exceptionIfNumberIsDuplicateTest(){
        log.info("Running test 1");
        final List<Integer> input = List.of(1,2,3,4,5,6,7);

        assertDoesNotThrow(() -> exceptionIfNumberIsDuplicate(input));

        log.info("Running test 2");
        final List<Integer> input2 = List.of(1,2,3,4,5,5,6);
        assertThrows(IllegalStateException.class,() -> exceptionIfNumberIsDuplicate(input2));
    }

    @Test
    void exceptionIfStringDoesNotContainVowelsTest(){
        log.info("Running test 1");
        assertDoesNotThrow(() -> exceptionIfStringDoesNotContainVowels("test"));

        log.info("Running test 2");
        assertThrows(MatchException.class,() -> exceptionIfStringDoesNotContainVowels("qwrtyp"));
    }
}
