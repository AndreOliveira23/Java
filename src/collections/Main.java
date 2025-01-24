package collections;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    //The Collections Framework is a set of interfaces that models different way of storing data in different types of containers.

    //Iterable: super interface of Collection. Implement Iterable = being able to iterate over

    /*
    |->List -> Have index for added elements
    |   |----->ArrayList
    |   |
    |   |----->LinkedList = doubly linked list implementation
    |   |
    |
    |
    |->Set -> Does not have index(or any particular order), and does not have duplicates
    |    |
    |    |----->TreeSet = implementation of SortedSet (interface that extends Set)
    |    |
    |
    |
    |
    |->Map -> Stores key-value pairs
    |
    |
    |
    |
    |
     */
    public static void main(String[] args) {
        Predicate<String> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

        List<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("");
        strings.add("one");
        strings.add("two");
        strings.add("");
        strings.add("three");
        strings.add(null);

        System.out.println("strings = " + strings);
        strings.removeIf(isNullOrEmpty);
        System.out.println("filtered strings = " + strings);

        Map<UUID,String> map = new HashMap<>();

        map.put(UUID.randomUUID(),"TEST");
        map.put(UUID.randomUUID(),"TEST2");
        System.out.println(map);


    }
}
