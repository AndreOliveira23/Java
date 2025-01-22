
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

import static lambdas.Exercises.*;
import static org.junit.jupiter.api.Assertions.*;

public class LambdasExercisesTest {

    Logger log = LoggerFactory.getLogger(LambdasExercisesTest.class);

    @Test
    void sumTwoIntegersTest(){
        int n1 = 5, n2 = 10;
        assertEquals(15,sumTwoIntegers.applyAsInt(n1,n2));
    }

    @Test
    void checkEmptyStringTest(){
        String emptyString = "";
        String nonEmptyString = "Non empty String";

        assertTrue(checkEmptyString.test(emptyString));
        assertFalse(checkEmptyString.test(nonEmptyString));
    }

    @Test
    void upperCaseAndLowerCaseTest(){
        String string = "Yesterday is history, tomorrow is a mystery";
        assertEquals(string.toUpperCase(),upperCase.apply(string));
        assertEquals(string.toLowerCase(),lowerCase.apply(string));
    }

    @Test
    void evenFilterTest(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,9);
        assertEquals(3, filterEven.apply(list).size());
        assertEquals(5, filterOdd.apply(list).size());
    }

    @Test
    void sortStringListTest(){
        List<String> input = List.of("e", "d", "c", "b","a");
        List<String> expected = List.of("a","b","c","d","e");
        assertEquals(expected,sortStringList.apply(input));
    }

    @Test
    void findAvgTest(){
        List<Double> input = List.of(5.0,5.2,4.3,4.90);
        assertEquals(4.85,findAvg.apply(input));
    }

    @Test
    void removeDuplicatesTest(){
       List<Integer> input = List.of(1,1,2,3,3,4,5,5,6,7,7,7,8,9,9,9,9);
       List<Integer> expected = List.of(1,2,3,4,5,6,7,8,9);
       assertEquals(expected,removeDuplicates.apply(input));
    }

    @Test
    void factorialTest(){
        assertEquals(1,factorial.apply(0));
        assertEquals(1,factorial.apply(1));
        assertEquals(2,factorial.apply(2));
        assertEquals(6,factorial.apply(3));
        assertEquals(24,factorial.apply(4));
        assertEquals(120,factorial.apply(5));
        assertEquals(720,factorial.apply(6));
        assertEquals(5040,factorial.apply(7));
        assertEquals(40320,factorial.apply(8));
        assertEquals(362880,factorial.apply(9));
        assertEquals(3628800,factorial.apply(10));
    }

    @Test
    void isPrimeTest(){
        assertTrue(isPrime.test(13));
        assertFalse(isPrime.test(9));
        assertTrue(isPrime.test(2));
        assertFalse(isPrime.test(4));
        assertTrue(isPrime.test(29));
        assertFalse(isPrime.test(100));
        assertTrue(isPrime.test(3));
        assertFalse(isPrime.test(15));
        assertTrue(isPrime.test(11));
        assertFalse(isPrime.test(6));
        assertTrue(isPrime.test(97));
        assertFalse(isPrime.test(-7));
    }

    @Test
    void concatTest(){
        String string = "Esta";
        String string1 = "é uma string concatenada";
        assertEquals(string+string1,concat.apply(string,string1));
    }

    @Test
    void findMaxTest(){
        assertEquals(4,findMax.apply(List.of(1,4,3)));
        assertEquals(123,findMax.apply(List.of(123,47,30)));
        assertEquals(456,findMax.apply(List.of(140,456,320)));
        assertEquals(789,findMax.apply(List.of(1,4,3,123,5,1,5,789,780)));
        assertEquals(125,findMax.apply(List.of(1,4,3,17,28,125,124,123)));
        assertEquals(484,findMax.apply(List.of(1,484,3,2,10,15,20)));
        assertEquals(43,findMax.apply(List.of(1,43,42,40,36,37)));
    }


    @Test
    void findMinTest(){
        assertEquals(1,findMin.apply(List.of(1,4,3)));
        assertEquals(30,findMin.apply(List.of(123,47,30)));
        assertEquals(140,findMin.apply(List.of(140,456,320)));
        assertEquals(1,findMin.apply(List.of(1,4,3,123,5,1,5,789,780)));
        assertEquals(1,findMin.apply(List.of(1,4,3,17,28,125,124,123)));
        assertEquals(1,findMin.apply(List.of(1,484,3,2,10,15,20)));
        assertEquals(1,findMin.apply(List.of(1,43,42,40,36,37)));
    }

    @Test
    void multiplyAllTest(){
        assertEquals(20,multiplyAll.apply(List.of(5,4)));
        //TODO
    }

    @Test
    void suMAllTest(){
        assertEquals(9,sumAll.apply(List.of(5,4)));
        //TODO
    }

    @Test
    void sumOfSquareOfAllOdsTest(){
        //1² + 3³ + 5² + 7² + 9²
        assertEquals(165,sumOfSquareOfAllOds.apply(List.of(1,2,3,4,5,6,7,8,9)));

        //TODO
    }

    @Test
    void sumOfSquareOfAllEvenTest(){
        //2² + 4² + 6² + 8²
        assertEquals(120,sumOfSquareOfAllEven.apply(List.of(1,2,3,4,5,6,7,8,9)));

        //TODO
    }

    @Test
    void countWordsTest(){
        assertEquals(5, countWords.apply("Esta string possui cinco palavras"));
        assertEquals(15, countWords.apply("Write a Java program to implement a lambda expression to count words in a sentence."));
    }

    @Test
    void checkContainsTest(){
        assertTrue(checkIfListContainWord.test(List.of("Teste","de","Lista","De","Strings"),"De"));
    }

}
