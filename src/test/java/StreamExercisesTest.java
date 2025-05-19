import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This set of exercises is about lambdas and method references. You will write a lambda or method
 * reference corresponding to each of several different functional interfaces. Each exercise is
 * named after the functional interface intended to be used as the solution.
 *
 *
 * <br><br>Reference:
 * <a href="https://github.com/ZahidFKhan/Streams-API-Practices">Streams-API-Practices repository by @ZahidFKhan</a>
 */

public class StreamExercisesTest {

    /**
     * Write a lambda expression that is a predicate that tests whether a string is longer than four
     * characters.
     */
    @Test
    public void a_predicate1() {
        Predicate<String> pred = s -> s.length() > 4;
        assertTrue(pred.test("abcde"));
        assertFalse(pred.test("abcd"));
    }

    /** Write a lambda expression that is a predicate that tests whether a string is empty. */
    @Test
    public void a_predicate2() {
        Predicate<String> pred = s -> s.isEmpty();
        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }

    /**
     * Write an unbound method reference that is a predicate that tests whether a string is empty. An
     * unbound method reference has a class projectName on the left-hand side of the :: operator:
     *
     * <p>classname::methodname
     */
    @Test
    public void a_predicate3() {
        Predicate<String> pred = String::isEmpty;

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }

    /** Create a predicate that returns true if both predicates startsWithJ and lengthIs7 hold. */
    @Test
    public void a_predicate4() {
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        Predicate<String> lengthIs7 = s -> s.length() == 7;

        Predicate<String> startsWithJAndLengthIs7 = startsWithJ.and(lengthIs7);

        assertFalse(startsWithJAndLengthIs7.test("Hello"));
        assertFalse(startsWithJAndLengthIs7.test("HelloJ1"));
        assertFalse(startsWithJAndLengthIs7.test("Java1"));
        assertTrue(startsWithJAndLengthIs7.test("JavaOne"));
    }

    /**
     * Create a predicate that is true if the length of the provided string is 9 or the provided
     * string equals ERROR.
     */
    @Test
    public void a_predicate5() {
        Predicate<String> lengthIs9 = s -> s.length() == 9;
        Predicate<String> equalsError = "ERROR"::equals;
        // Note: this could also be: Predicate.isEqual("ERROR")

        Predicate<String> lengthIs9orError = lengthIs9.or(equalsError);

        assertFalse(lengthIs9orError.test("Hello"));
        assertTrue(lengthIs9orError.test("Hello J1!"));
        assertTrue(lengthIs9orError.test("ERROR"));
        assertFalse(lengthIs9orError.test("Error"));
    }

    /** Write a lambda expression that wraps the given string in parentheses. */
    @Test
    public void b_function1() {
        Function<String, String> func = s -> "("+s+")";
        assertEquals("(abc)", func.apply("abc"));
    }

    /** Write a lambda expression that converts the given string to upper case. */
    @Test
    public void b_function2() {
        Function<String, String> func = s -> s.toUpperCase();
        assertEquals("ABC", func.apply("abc"));
    }

    /** Write an unbound method reference that converts the given string to upper case. */
    @Test
    public void b_function3() {
        Function<String, String> func = String::toUpperCase;
        assertEquals("ABC", func.apply("abc"));
    }

    /**
     * Given two Functions, one that converts a null reference to an empty string, and another that
     * gets the length of a string, create a single function converts nulls and then gets the string's
     * length.
     */
    @Test
    public void b_function4() {
        Function<String, String> unNullify = s -> s == null ? "" : s;
        Function<String, Integer> length = String::length;

        Function<String, Integer> lengthBis = unNullify.andThen(length);

        assertEquals((Integer) 14, lengthBis.apply("Hello JavaOne!"));
        assertEquals((Integer) 0, lengthBis.apply(""));
        assertEquals((Integer) 0, lengthBis.apply(null));
    }

    /** Write a lambda expression that appends the string "abc" to the given StringBuilder. */
    @Test
    public void c_consumer1() {
        Consumer<StringBuilder> cons = sb -> sb.append("abc");

        StringBuilder sb = new StringBuilder("xyz");
        cons.accept(sb);
        assertEquals("xyzabc", sb.toString());
    }

    /** Write a lambda expression that clears the given list. */
    @Test
    public void c_consumer2() {
        Consumer<List<String>> cons = list -> list.clear();

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /** Write an unbound method reference that clears the given list. */
    @Test
    public void c_consumer3() {
        Consumer<List<String>> cons =  List::clear;

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /**
     * Given two consumers, create a consumer that passes the String to the first consumer, then to
     * the second.
     */
    @Test
    public void c_consumer4() {
        Consumer<List<String>> c1 = list -> list.add("first");
        Consumer<List<String>> c2 = list -> list.add("second");

        Consumer<List<String>> consumer = c1.andThen(c2);

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        consumer.accept(list);
        assertEquals(List.of("a", "b", "c", "first", "second"), list);
    }

    /** Write a lambda expression that returns a new StringBuilder containing the string "abc". */
    @Test
    public void d_supplier1() {
        Supplier<StringBuilder> sup = new Supplier<StringBuilder>() {
            @Override
            public StringBuilder get() {
                return new StringBuilder("abc");
            }
        };

        assertEquals("abc", sup.get().toString());
    }

    /** Write a lambda expression that returns a new, empty StringBuilder. */
    @Test

    public void d_supplier2() {
        Supplier<StringBuilder> sup = StringBuilder::new;

        assertEquals("", sup.get().toString());
    }

    /** Write a constructor reference that returns a new, empty StringBuilder. */
    @Test

    public void d_supplier3() {
        Supplier<StringBuilder> sup = StringBuilder::new;

        assertEquals("", sup.get().toString());
    }

    /**
     * Write a lambda expression that, given two strings, returns the result of concatenating the
     * first with the second, followed by the first again.
     */
    @Test
    public void e_bifunction1() {
        BiFunction<String, String, String> bifunc = (s1,s2) -> s1+s2+s1;
        assertEquals("FirstSecondFirst", bifunc.apply("First", "Second"));
    }

    /**
     * Write a lambda expression that returns the index of the first occurrence of the second string
     * within the first string, or -1 if the second string doesn't occur within the first string.
     */
    @Test
    public void e_bifunction2() {
        BiFunction<String, String, Integer> bifunc = (s1,s2) -> s1.indexOf(s2);

        assertEquals(3, bifunc.apply("abcdefghi", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghi", "xyz").intValue());
    }

    /**
     * Write an unbound method reference that returns the index of the first occurrence of the second
     * string within the first string, or -1 if the second string doesn't occur within the first
     * string.
     */
    @Test
    public void e_bifunction3() {
        BiFunction<String, String, Integer> bifunc = String::indexOf;

        assertEquals(3, bifunc.apply("abcdefghij", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghij", "xyz").intValue());
    }

    /**
     * Write a lambda expression that appends the 'suffix' variable (a String) to the sb variable (a
     * StringBuilder).
     */
    @Test
    public void f_runnable1() {
        StringBuilder sb = new StringBuilder("abc");
        String suffix = "xyz";

        Runnable r = new Runnable() {
            @Override
            public void run() {
                sb.append(suffix);
            }
        };

        r.run();
        r.run();
        r.run();
        assertEquals("abcxyzxyzxyz", sb.toString());
    }

    /**
     * Write a lambda expression that takes a string argument and returns the index of that argument
     * into the string "abcdefghij", or that returns -1 if the string argument doesn't occur.
     */
    @Test
    public void g_boundMethodRef1() {
        Function<String, Integer> func = s-> "abcdefghij".indexOf(s);

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }

    /**
     * Write a bound method reference that takes a string argument and returns the index of that
     * argument into the string "abcdefghij", or that returns -1 if the string argument doesn't occur.
     * A bound method reference has an instance, or an expression that evaluates to an instance, on
     * the left-hand side of the :: operator:
     *
     * <p>myObject::methodname
     *
     * <p>This is in contrast to an unbound method reference, which has a classname on the left-hand
     * side of the :: operator.
     */
    @Test
    public void g_boundMethodRef2() {
        Function<String, Integer> func = "abcdefghij"::indexOf;

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }
    private record Person (String name, String LastName, int age){}

    final Person ayman = new Person("Ayman", "Khan", 51);
    final Person rod = new Person("Rod", "Stewart", 71);
    final Person paul = new Person("Paul", "McCartney", 74);
    final Person mick = new Person("Mick", "Jagger", 73);
    final Person jermaine = new Person("Jermaine", "Jackson", 61);

    /**
     * Write a Comparator that compare instances of String using their length. For instance FOUR (4
     * letters) is greater than TWO (three letters)
     */
    @Test
    public void comparator01() {
        Comparator<String> compareByLength = Comparator.comparingInt(String::length);

        assertTrue(compareByLength.compare("FOUR", "TWO") > 0);
        assertTrue(compareByLength.compare("ONE", "SEVEN") < 0);
        assertTrue(compareByLength.compare("ONE", "TWO") == 0);
    }

    /**
     * Write a Comparator that compare instances of String using their length. If the lengths are the
     * same, then use the alphabetical order.
     */
    @Test
    public void comparator02() {
        Comparator<String> compareByLengthThenAlphabetical =
                Comparator.comparingInt(String::length).thenComparing(String::toString);

        Assertions.assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "TWO") > 0);
        Assertions.assertTrue(compareByLengthThenAlphabetical.compare("ONE", "SEVEN") < 0);
        Assertions.assertTrue(compareByLengthThenAlphabetical.compare("ONE", "TWO") < 0);
        Assertions.assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "FIVE") > 0);
        Assertions.assertTrue(compareByLengthThenAlphabetical.compare("EIGHT", "EIGHT") == 0);
    }

    /** Write a Comparator that compares instances of Person using their lastName. */
    @Test
    public void comparator03() {
        Comparator<Person> comparebyLastName = Comparator.comparing(Person::LastName);

        Assertions.assertTrue(comparebyLastName.compare(ayman, rod) < 0);
        Assertions.assertTrue(comparebyLastName.compare(paul, paul) == 0);
        Assertions.assertTrue(comparebyLastName.compare(mick, jermaine) > 0);
    }

    /**
     * Write a Comparator that compares instances of Person using their lastName, and if their last
     * projectName is the same, uses their first projectName.
     */
    @Test
    public void comparator04() {
        Comparator<Person> comparebyLastNameThenFirstName =
                Comparator.comparing(Person::LastName).thenComparing(Person::name);

        assertTrue(comparebyLastNameThenFirstName.compare(ayman, rod) < 0);
        assertTrue(comparebyLastNameThenFirstName.compare(paul, paul) == 0);
        assertTrue(comparebyLastNameThenFirstName.compare(ayman, jermaine) > 0);
    }

    /**
     * Write a Comparator that compares the people in the order reversed from the one you wrote in the
     * comparator04() exercise. That is, the person with the greater last projectName should be
     * ordered first. If two persons have the same last projectName, the one with the greater first
     * projectName should be ordered first.
     */
    @Test
    public void comparator05() {
        Comparator<Person> comparebyLastNameThenFirstNameReversed = Comparator.comparing
                                                                    (Person::LastName).thenComparing(Person::name)
                                                                    .reversed();

        assertFalse(comparebyLastNameThenFirstNameReversed.compare(ayman, rod) < 0);
        assertTrue(comparebyLastNameThenFirstNameReversed.compare(paul, paul) == 0);
        assertFalse(comparebyLastNameThenFirstNameReversed.compare(ayman, jermaine) > 0);
    }

    /**
     * Write a Comparator that compares the people in the same order as the one you wrote in
     * comparator04(), but that supports null values. The null values should be considered greater
     * than any non-null values.
     */
    @Test
    public void comparator06() {
        Comparator<Person> comparebyLastNameThenFirstNameWithNull =
                Comparator.nullsLast(Comparator.comparing(Person::LastName))
                                .thenComparing(Comparator.nullsLast(Comparator.comparing(Person::name)));

        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(ayman, rod) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(paul, paul) == 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(ayman, jermaine) > 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(mick, null) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(null, mick) > 0);
    }

    /**
     * Write a Comparator that compares two people by age. Try to write the comparator to avoid
     * boxing of primitives.
     */
    @Test
    public void comparator07() {
        Comparator<Person> comparebyAge = Comparator.comparingInt(Person::age);

        assertTrue(comparebyAge.compare(ayman, rod) < 0);
        assertTrue(comparebyAge.compare(paul, paul) == 0);
        assertTrue(comparebyAge.compare(mick, jermaine) > 0);
    }

    /**
     * Write a lambda expression that compares two int values and returns an int result that is less
     * than, equal to, or greater than zero, like a comparator. Watch out for overflow. The Comparator
     * interface takes two objects, but in this case we are comparing int primitives, so the
     * functional interface we use is IntBinaryOperator.
     */
    @Test
    public void comparator08() {
        IntBinaryOperator intCompare = (n1,n2) -> Integer.compare(n1, n2);

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }

    /**
     * Write a method reference that compares two int values and returns an int result that is less
     * than, equal to, or greater than zero, like a comparator.
     */
    @Test
    public void comparator09() {
        IntBinaryOperator intCompare = Integer::compare;

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }

    /**
     * Write a method reference that compares two double values and returns an int result that is less
     * than, equal to, or greater than zero, like a comparator. There functional interface that takes
     * two doubles and returns an int, so we define one here. Comparing double values introduces
     * special cases such NaN. Consider all NaN values to be equal to each other and greater than any
     * non-NaN value.
     */
    @Test
    public void comparator10() {
        DoubleToIntBiFunction doubleCompare = Double::compare;

        assertTrue(doubleCompare.applyAsInt(0.0, 1.0) < 0);
        assertTrue(doubleCompare.applyAsInt(1.0, 1.0) == 0);
        assertTrue(doubleCompare.applyAsInt(2.0, 1.0) > 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, Double.NaN) == 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, 0.0) > 0);
        assertTrue(doubleCompare.applyAsInt(0.0, Double.NaN) < 0);
    }

    interface DoubleToIntBiFunction {
        int applyAsInt(double a, double b);
    }

    /**
     * Given a list of StringBuilders, modify each StringBuilder in-place by appending the string
     * "new" to each one.
     */
    @Test
    public void c01_appendNew() {
        List<StringBuilder> sbList =
                List.of(
                        new StringBuilder("alfa"), new StringBuilder("bravo"), new StringBuilder("charlie"));

        sbList.forEach(e -> e.append("new"));

        Assertions.assertEquals(
                List.of("alfanew", "bravonew", "charlienew"),
                sbList.stream().map(StringBuilder::toString).collect(Collectors.toList()));
    }

    /** Remove the words that have odd lengths from the list. */
    @Test
    public void c02_removeOddLengthWords() {
        List<String> list =
                new ArrayList<>(Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        list.removeIf(s -> s.length() % 2 !=0);

        Assertions.assertEquals(List.of("alfa", "echo"), list);
    }

    /** Replace every word in the list with its upper case equivalent. */
    @Test
    public void c03_upcaseAllWords() {
        List<String> list = Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        list.replaceAll(String::toUpperCase);

        Assertions.assertEquals(List.of("ALFA", "BRAVO", "CHARLIE", "DELTA", "ECHO", "FOXTROT"), list);
    }

    /**
     * Given a map whose keys are Integers and whose values are StringBuilders, append to each
     * StringBuilder the string representation of its corresponding Integer key. This should mutate
     * each StringBuilder value in-place.
     */
    @Test
    public void c04_appendToMapValues() {
        Map<Integer, StringBuilder> map = new TreeMap<>();
        map.put(1, new StringBuilder("alfa"));
        map.put(2, new StringBuilder("bravo"));
        map.put(3, new StringBuilder("charlie"));

        map.forEach((i,sb) -> map.get(i).append(i.toString()));

        Assertions.assertEquals(3, map.size());
        assertTrue(map.values().stream().allMatch(x -> x instanceof StringBuilder));
        assertEquals("alfa1", map.get(1).toString());
        assertEquals("bravo2", map.get(2).toString());
        assertEquals("charlie3", map.get(3).toString());
    }

    /**
     * Given a map whose keys are Integers and whose values are Strings, append to each String the
     * string representation of its corresponding Integer key.
     */
    @Test
    public void c05_replaceMapValues() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "alfa");
        map.put(2, "bravo");
        map.put(3, "charlie");

        map.forEach((i, s) -> map.put(i, map.get(i).concat(i.toString())));

        Assertions.assertEquals(Map.of(1, "alfa1", 2, "bravo2", 3, "charlie3"), map);
    }

    /**
     * Given a list of words, populate a map whose keys are the lengths of each word, and whose values
     * are list of words with that length.
     */
    @Test
    public void c06_mapOfListOfStringsByLength() {
        List<String> list =
                List.of(
                        "aardvark",
                        "bison",
                        "capybara",
                        "alligator",
                        "bushbaby",
                        "chimpanzee",
                        "avocet",
                        "bustard",
                        "capuchin");
        Map<Integer, List<String>> result = new TreeMap<>();

        result = list.stream().collect(Collectors.groupingBy(String::length));

        Assertions.assertEquals(
                Map.of(
                        5,
                        List.of("bison"),
                        6,
                        List.of("avocet"),
                        7,
                        List.of("bustard"),
                        8,
                        List.of("aardvark", "capybara", "bushbaby", "capuchin"),
                        9,
                        List.of("alligator"),
                        10,
                        List.of("chimpanzee")),
                result);
    }

    /**
     * Given a list of words, populate a map whose keys are the initial characters of each word, and
     * whose values are the concatenation of the words with that initial character. When concatenating
     * the words, they should be separated by a colon (':').
     */
    @Test
    public void c07_mapOfStringByInitialCharacter() {
        List<String> list =
                List.of(
                        "aardvark",
                        "bison",
                        "capybara",
                        "alligator",
                        "bushbaby",
                        "chimpanzee",
                        "avocet",
                        "bustard",
                        "capuchin");
        Map<Character, String> result = new TreeMap<>();

        result = list.stream().collect(
                Collectors.groupingBy(
                s -> s.charAt(0),Collectors.joining(":")
        ));

        Assertions.assertEquals(
                Map.of(
                        'a',
                        "aardvark:alligator:avocet",
                        'b',
                        "bison:bushbaby:bustard",
                        'c',
                        "capybara:chimpanzee:capuchin"),
                result);
    }


    /**
     * For some reason the provided map doesn't have mappings for all the keys. This is a problem,
     * because if we call get() on a key that isn't present, it returns null, and we need to add
     * checks to protect against NullPointerException. Write code to ensure that all missing keys are
     * mapped to the empty string.
     */
    @Test
    public void c08_mapWithMissingValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map =
                new HashMap<>(Map.of("a", "alfa", "b", "bravo", "c", "charlie", "d", "delta"));

        keys.forEach(k -> map.putIfAbsent(k,""));

        Assertions.assertEquals(
                Map.of("a", "alfa", "b", "bravo", "c", "charlie", "d", "delta", "e", "", "f", "", "g", ""),
                map);
    }


    /**
     * In the previous example, we added map entries that had a default value. We've now determined
     * that's incorrect, and we want to undo that. This time, we want to remove the entry if the value
     * is the empty string.
     */
    @Test
    public void c09_mapRemoveEntriesWithEmptyValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map =
                new HashMap<>(
                        Map.of(
                                "a", "alfa", "b", "bravo", "c", "charlie", "d", "delta", "e", "", "f", "", "g",
                                ""));

        keys.forEach(key -> {
            if(map.get(key).isEmpty()) map.remove(key); }
        );


        Assertions.assertEquals(Map.of("a", "alfa", "b", "bravo", "c", "charlie", "d", "delta"), map);
    }

    /**
     * We need another way to deal with the problem of the previous example. Instead of removing
     * entries whose value is the empty string, we want to replace the empty-string values with a
     * value that's the key itself. Write the code to do that.
     */
    @Test
    public void c10_mapReplaceEmptyValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map =
                new HashMap<>(
                        Map.of(
                                "a", "alfa", "b", "bravo", "c", "charlie", "d", "delta", "e", "", "f", "", "g",
                                ""));

        keys.forEach(key -> {
            if(map.get(key).isEmpty()) map.put(key,key); }
        );

        Assertions.assertEquals(
                Map.of(
                        "a", "alfa", "b", "bravo", "c", "charlie", "d", "delta", "e", "e", "f", "f", "g", "g"),
                map);
    }

    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.replace() default method that takes 3 arguments.
    // </editor-fold>

    /**
     * We are still dealing with a map with missing entries. For entries that are present, we want to
     * convert the value to upper case; and for keys that are not present, we want to add an entry
     * where the value is the same as the key.
     */
    @Test
    public void c11_computeWithMissingEntries() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map =
                new HashMap<>(Map.of("a", "alfa", "b", "bravo", "c", "charlie", "d", "delta"));

        keys.forEach(key -> {
            if (map.get(key) != null) {
                map.replace(key, map.get(key), map.get(key).toUpperCase());
            }else {
                map.put(key, key);
            }
        });

        Assertions.assertEquals(
                Map.of(
                        "a", "ALFA", "b", "BRAVO", "c", "CHARLIE", "d", "DELTA", "e", "e", "f", "f", "g", "g"),
                map);
    }

    /**
     * The map now has several entries, some with valid values, and some with values that are the
     * empty string. This time, we want to convert the non-empty values to upper case, but we want to
     * remove the entries for which the values are the empty string.
     */
    @Test
    public void c12_computeAndRemoveSomeEntries() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map =
                new HashMap<>(
                        Map.of(
                                "a", "alfa", "b", "bravo", "c", "charlie", "d", "delta", "e", "", "f", "", "g",
                                ""));

        Predicate<String> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNullOrIsEmpty = isNull.or(isEmpty);

        keys.forEach((k) -> {
            if(!isNullOrIsEmpty.test(map.get(k))) {
                map.replace(k,map.get(k),map.get(k).toUpperCase());
            }
            if(map.get(k).isEmpty()) map.remove(k);
        });
        Assertions.assertEquals(Map.of("a", "ALFA", "b", "BRAVO", "c", "CHARLIE", "d", "DELTA"), map);
    }

    private BufferedReader reader;

    /**
     * Given a list of words, create an output list that contains only the odd-length words, converted
     * to upper case.
     */
    @Test
    public void d1_upcaseOddLengthWords() {
        List<String> input = List.of("alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        Predicate<String> OddLength = s -> s.length() % 2 !=0;
        Function<String,String> toUpperCase = String::toUpperCase;

        List<String> result = input.stream().map(toUpperCase).filter(OddLength).toList();

        Assertions.assertEquals(List.of("BRAVO", "CHARLIE", "DELTA", "FOXTROT"), result);
    }

    /**
     * Take the third through fifth words of the list, extract the second letter from each, and join
     * them, separated by commas, into a single string. Watch for off-by-one errors.
     */
    @Test
    public void d2_joinStreamRange() {
        List<String> input = List.of("alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        Function<String,Character> getSecondLetter  = s -> s.charAt(1);

        StringBuilder sb = new StringBuilder();
        String result = "";


        input.subList(2,5).stream().map(getSecondLetter).forEach( s -> sb.append(s).append(","));
        result = sb.substring(0,sb.length()-1);

        Assertions.assertEquals("h,e,c", result);
    }
    /**
     * Count the number of lines in the text file. (Remember to use the BufferedReader named "reader"
     * that has already been opened for you.)
     *
     * @throws IOException
     */
    @Test
    public void d3_countLinesInFile() throws IOException {
        long count = 0;
        count = reader.lines().count();

        Assertions.assertEquals(14, count);
    }

    /**
     * Find the length of the longest line in the text file.
     *
     * @throws IOException
     */
    @Test
    public void d4_findLengthOfLongestLine() throws IOException {
        int longestLength = 0; // TODO

        longestLength = reader.lines()
                .max(Comparator.comparingInt(String::length))
                .map(String::length)
                .orElse(0);

        Assertions.assertEquals(53, longestLength);
    }

    /**
     * Find the longest line in the text file.
     *
     * @throws IOException
     */
    @Test
    public void d5_findLongestLine() throws IOException {
        String longest = null; // TODO

        longest = reader.lines()
                        .max(Comparator.comparingInt(String::length))
                                .orElse(null);

        Assertions.assertEquals("Feed'st thy light's flame with self-substantial fuel,", longest);
    }

    /**
     * Select the longest words from the input list. That is, select the words whose lengths are equal
     * to the maximum word length.
     */
    @Test
    public void d6_selectLongestWords() {
        List<String> input =
                List.of("alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = null; // TODO

        int maxLength = input.stream().max(Comparator.comparingInt(String::length)).get().length();

        result = input.stream().filter(s -> s.length() == maxLength).toList();

        Assertions.assertEquals(List.of("charlie", "foxtrot"), result);
    }

    // ========================================================
    // END OF EXERCISES
    // TEST INFRASTRUCTURE IS BELOW
    // ========================================================

    @BeforeEach
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(Paths.get("src/test/java/SonnetI.txt"), StandardCharsets.UTF_8);
    }

    @AfterEach
    public void z_closeBufferedReader() throws IOException {
        reader.close();
    }
}